package com.example.todosappmongodb.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.todosappmongodb.R
import com.example.todosappmongodb.databinding.FragmentHomeBinding
import com.example.todosappmongodb.databinding.FragmentRegisterBinding
import com.example.todosappmongodb.utils.Constants
import com.example.todosappmongodb.utils.Constants.TAG
import com.example.todosappmongodb.utils.NetworkResult
import com.example.todosappmongodb.utils.Utils
import com.example.todosappmongodb.utils.ViewExtensionFunctions.hide
import com.example.todosappmongodb.utils.ViewExtensionFunctions.show
import com.example.todosappmongodb.viewmodel.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : Fragment()
{


    private var _binding: FragmentRegisterBinding?=null;
    private  val binding  get() = _binding!!
    val authViewModel: AuthViewModel by viewModels();
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        _binding = FragmentRegisterBinding.inflate(inflater,container,false)

        // navigate to Log in
        binding.buttonRegisterFragmentLogin.setOnClickListener()
        {
            findNavController().navigate(R.id.action_registerFragment_to_logInFragment)
        }



        // Log into your account
        binding.buttonRegisterFragmentRegister.setOnClickListener()
        {
            val name = binding.editTextRegisterFragmentName.text.toString().trim();
            val email = binding.editTextRegisterFragmentEmail.text.toString().trim();
            val password = binding.editTextRegisterFragmentPassword.text.toString().trim();

            val validate = Utils.checkRegisterFields(name, email, password,);
            if (validate)
            {
                Log.d(Constants.TAG, "onCreateView: Register")
                authViewModel.authenticateUser(name,email,password)
            }
            else
            {
                Toast.makeText(requireContext(), "Please Enter All Fields", Toast.LENGTH_SHORT).show()
            }

        } // buttonRegister closed

            observerUserResponse()
            return  binding.root
    } // onCreate view closed


    private fun observerUserResponse()
    {
        authViewModel.user.observe(viewLifecycleOwner)
        {
                response ->
            when(response)
            {
                is NetworkResult.Loading -> binding.progressBarRegisterFragment.show()
                is NetworkResult.Error   ->
                {
                    binding.progressBarRegisterFragment.hide()
                    Toast.makeText(requireContext(), response.message, Toast.LENGTH_SHORT).show();
                    Log.d(Constants.TAG, "observerUserResponse:Error message "+response.message)
                } // is NetworkResult.Error closed
                is NetworkResult.Success ->
                {
                    binding.progressBarRegisterFragment.hide();
                    Toast.makeText(requireContext(), "Success:", Toast.LENGTH_SHORT).show()
                    Log.d(TAG, "observerUserResponse:Success :")
                    Log.d(TAG, "observerUserResponse: "+response.data)
                } // is NetworkResultSuccess closed
            } // when closed

        } // observer closed
    } // observerUserResponse closed

} // class closed


