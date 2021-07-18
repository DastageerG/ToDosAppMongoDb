package com.example.todosappmongodb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.todosappmongodb.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity()
{

    private lateinit var navController:NavController;
    var _binding:ActivityMainBinding?=null;
    val binding  get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater);
        setContentView(binding.root)

        navController = findNavController(R.id.nav_host)

        val appBarConfiguration = AppBarConfiguration(
            setOf
                (
                R.id.logInFragment
                ,R.id.homeFragment
                ,R.id.registerFragment
            ))


        setupActionBarWithNavController(navController,appBarConfiguration)





    }

    override fun onSupportNavigateUp(): Boolean
    {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }


}