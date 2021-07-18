package com.example.todosappmongodb.viewmodel

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.text.TextUtils
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todosappmongodb.model.UserResponse
import com.example.todosappmongodb.repository.Repository
import com.example.todosappmongodb.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.Dispatcher
import retrofit2.Response
import javax.inject.Inject
import kotlin.math.log

@HiltViewModel
class MainViewModel @Inject constructor  (private val repository: Repository,application: Application) :  AndroidViewModel(application)
{







} // MainViewModel closed