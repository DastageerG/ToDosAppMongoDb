package com.example.todosappmongodb.viewmodel

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.todosappmongodb.data.DataStoreRepo
import com.example.todosappmongodb.model.UserResponse
import com.example.todosappmongodb.repository.Repository
import com.example.todosappmongodb.utils.Constants.EMAIL
import com.example.todosappmongodb.utils.Constants.PASSWORD
import com.example.todosappmongodb.utils.Constants.TAG
import com.example.todosappmongodb.utils.Constants.USER_NAME
import com.example.todosappmongodb.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject


@HiltViewModel
class AuthViewModel @Inject constructor  (private val repository: Repository,application: Application,private val dataStoreRepo: DataStoreRepo) :  AndroidViewModel(application)
{

    // manage user ///
    val getToken  = dataStoreRepo.getToken.asLiveData()

    fun saveToken(token:String) = viewModelScope.launch (Dispatchers.IO)
    {
        dataStoreRepo.saveToken(token)
    } // saveToken closed


    fun clearToken() = viewModelScope.launch (Dispatchers.IO)
    {
        dataStoreRepo.clearToken()
    } // saveToken closed


    //////////////  Authenticate User  //////////////////////
    var user : MutableLiveData<NetworkResult<UserResponse>> = MutableLiveData();
    val authData = HashMap<String,String>()
    lateinit var response: Response<UserResponse>;


    fun authenticateUser(name:String?=null,email:String,password:String) = viewModelScope.launch(Dispatchers.IO)
        {

            user.postValue(NetworkResult.Loading())
            Log.d(TAG, "authenticateUser: Loading ")
            if(hasInternetConnection())
            {
                try
                {
//                    when name is null exist then Register
                    if(name !=null)
                    {

                        authData.put(USER_NAME,name)
                        authData.put(EMAIL,email)
                        authData.put(PASSWORD,password)
                        response = repository.register(authData)
                        user.postValue(handleUserResponse(response))

                        Log.d(TAG, "authenticateUser: registering when name is present")
                    }
                    else
                    {
                        authData.put(EMAIL,email)
                        authData.put(PASSWORD,password)
                        response = repository.login(authData)
                        user.postValue(handleUserResponse(response))
                    } // else closed


                } // try  closed
                catch (e:Exception)
                {
                   user.postValue(NetworkResult.Error(e.message.toString()))
                    Log.d(TAG, "authenticateUser: Exception : "+e.toString())
                    Log.d(TAG, "authenticateUser:Exception msg "+e.message)
                } // catch closed

            } // if closed
            else
            {
                user.postValue(NetworkResult.Error("No Internet Connection"))
            } // else closed


        } // viewModelScope closed

    private fun handleUserResponse(response: Response<UserResponse>): NetworkResult<UserResponse>
    {
        return when
        {
            response.message().toString().contains("timeout") ->  NetworkResult.Error("Time Out.")
            response.code() == 402                          -> NetworkResult.Error("Error Occured.")
            response.body()!!.success.equals("false") -> NetworkResult.Error("User does not exist")
            response.isSuccessful                           -> NetworkResult.Success(response.body()!!)
            else ->
            {
                NetworkResult.Error(response.message().toString())
            }
        } // return when closed
    }


    // check Network Availability
    private fun hasInternetConnection() : Boolean
    {
        val connectivityManager = getApplication<Application>()
            .getSystemService(Context.CONNECTIVITY_SERVICE)
                as ConnectivityManager
        val activeNetwork = connectivityManager.activeNetwork ?: return false
        val capabilities = connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false

        return when
        {
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)     -> true
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            else                                                              -> false
        } // return When closed
    } // hasInternetConnection





}