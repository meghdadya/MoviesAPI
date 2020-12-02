package com.github.moviesapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.github.moviesapi.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


    }


    fun showProgress() {
        binding.mainActivityLoading.visibility = View.VISIBLE
    }
    fun hideProgress() {
        binding.mainActivityLoading.visibility = View.INVISIBLE
    }
    fun showToolbar() {
        binding.toolbarLayout.toolbar.visibility = View.VISIBLE
    }
    fun hideToolbar() {
        binding.toolbarLayout.toolbar.visibility = View.INVISIBLE
    }

}