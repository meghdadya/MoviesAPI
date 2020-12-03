package com.github.moviesapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.github.moviesapi.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        window.statusBarColor = ContextCompat.getColor(this, R.color.bg_color)

    }


    fun showProgress() {
        if (this::binding.isInitialized)
            binding.mainActivityLoading.visibility = View.VISIBLE
    }

    fun hideProgress() {
        if (this::binding.isInitialized)
            binding.mainActivityLoading.visibility = View.GONE
    }

    fun showToolbar() {
        if (this::binding.isInitialized)
            binding.toolbar.visibility = View.VISIBLE
    }

    fun hideToolbar() {
        if (this::binding.isInitialized)
            binding.toolbar.visibility = View.GONE
    }

}