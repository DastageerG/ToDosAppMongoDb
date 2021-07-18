package com.example.todosappmongodb.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.todosappmongodb.R
import com.example.todosappmongodb.databinding.ActivityMainBinding
import com.example.todosappmongodb.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment()
{

    var _binding:FragmentHomeBinding?=null;
    val binding  get() = _binding!!
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        _binding = FragmentHomeBinding.inflate(inflater,container,false)


        return  binding.root
    }

}