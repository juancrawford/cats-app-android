package com.catsapp.android.presentation.splash

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.catsapp.android.databinding.ActivitySplashBinding
import com.catsapp.android.utils.State
import com.catsapp.android.presentation.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

/**
 * Splash screen activity of the application.
 *
 * This activity displays a splash screen while initializing the app. It uses a ViewModel
 * to manage the logic related to the splash screen.
 */
@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {
    private val viewModel: SplashViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivitySplashBinding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Launch the app logic using the SplashViewModel
        lifecycleScope.launch {
            viewModel.launchApp().collect { state ->
                if (state is State.DataState) {
                    // Proceed to MainActivity when data state indicates app launch
                    if (state.data) {
                        startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                    }
                }
            }
        }
    }
}