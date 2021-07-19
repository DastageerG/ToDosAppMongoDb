package com.example.todosappmongodb.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.todosappmongodb.MainActivity
import com.example.todosappmongodb.R
import com.example.todosappmongodb.databinding.FragmentHomeBinding
import com.example.todosappmongodb.databinding.FragmentLogInBinding
import com.example.todosappmongodb.utils.Constants
import com.example.todosappmongodb.utils.Constants.TAG
import com.example.todosappmongodb.utils.NetworkResult
import com.example.todosappmongodb.utils.Utils
import com.example.todosappmongodb.utils.ViewExtensionFunctions
import com.example.todosappmongodb.utils.ViewExtensionFunctions.hide
import com.example.todosappmongodb.utils.ViewExtensionFunctions.show
import com.example.todosappmongodb.viewmodel.AuthViewModel
import com.example.todosappmongodb.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.log


@AndroidEntryPoint
class LogInFragment : Fragment()
{


    var _binding: FragmentLogInBinding?=null;
    val binding  get() = _binding!!

    val authViewModel:AuthViewModel by viewModels();

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        _binding = FragmentLogInBinding.inflate(inflater,container,false)

        // navigate to create an account
        binding.buttonLogInFragmentRegister.setOnClickListener()
        {
            findNavController().navigate(R.id.action_logInFragment_to_registerFragment)
        } // logInButton closed


        // Log into your account
        binding.buttonLogInFragmentLogin.setOnClickListener()
        {

            val email = binding.editTextLogInFragmentEmail.text.toString().trim();
            val password = binding.editTextLogInFragmentPassword.text.toString().trim();

            val validate = Utils.checkLoginFields(email,password);
            if(validate)
            {
               authViewModel.authenticateUser(email = email,password = password)
            }
            else
            {
                Toast.makeText(requireContext(),"Please Enter All Fields",Toast.LENGTH_SHORT).show()
            }


        } // Log in event  closed

        observerUserResponse()

        return  binding.root
    } // onCreateView closed


    private fun observerUserResponse()
    {
        authViewModel.user.observe(viewLifecycleOwner)
        {
            response ->
            when(response)
            {
                is NetworkResult.Loading -> binding.progressBarLogInFragment.show()
                is NetworkResult.Error ->
                {
                    Log.d(TAG, "observerUserResponse: "+response.data)
                    binding.progressBarLogInFragment.hide()
                    //Toast.makeText(requireContext(), response.message, Toast.LENGTH_SHORT).show();
                } // is NetworkResult.Error closed
                is NetworkResult.Success ->
                {
                    binding.progressBarLogInFragment.hide();
                    Toast.makeText(requireContext(), response.data?.msg, Toast.LENGTH_SHORT).show()
                    Log.d(TAG, "observerUserResponse: "+response.data)
                    response.data?.token.let()
                    {
                        authViewModel.saveToken(it.toString())
                    } // let closed

                } // is NetworkResultSuccess closed
            } // when closed

        } // observer closed
    } // observerUserResponse closed


    override fun onStart()
    {
        super.onStart()

        authViewModel.getToken.observe(viewLifecycleOwner)
        {
            token ->
            if(!(token.toString().equals("empty")) && token !=null)
            {
                findNavController().navigate(R.id.action_logInFragment_to_homeFragment)
            }
            Log.d(TAG, "onStart: "+token)

        } // observer closed

    } // onStart closed

} // LogInFragment closed