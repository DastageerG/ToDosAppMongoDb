package com.example.todosappmongodb.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.todosappmongodb.R
import com.example.todosappmongodb.databinding.ActivityMainBinding
import com.example.todosappmongodb.databinding.FragmentHomeBinding
import com.example.todosappmongodb.viewmodel.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment()
{

    var _binding:FragmentHomeBinding?=null;
    val binding  get() = _binding!!
    val authViewModel: AuthViewModel by viewModels();
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        _binding = FragmentHomeBinding.inflate(inflater,container,false)
        setHasOptionsMenu(true)


        return  binding.root
    } // onCreate closed

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater)
    {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_home_fragment,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean
    {
        when(item.itemId)
        {
            R.id.menuHomeFragmentLogOut ->
            {
                authViewModel.clearToken()
                findNavController().navigate(R.id.action_homeFragment_to_logInFragment)
            }
        }
        return super.onOptionsItemSelected(item)
    }

} // class closed