package com.example.splash.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.IdRes
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.common.domain.model.HOME_DEEPLINK
import com.example.common.domain.model.ONBOARDING_DEEPLINK
import com.example.splash.R
import com.example.splash.databinding.FragmentSplashBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class SplashFragment : Fragment() {

    private lateinit var binding: FragmentSplashBinding

    private val viewModel: SplashViewModel by viewModels()

    @Inject
    lateinit var splashNavigation: SplashNavigation


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSplashBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupCollectors()
        viewModel.onEvent(SplashActivityEvent.DefineStartDestination)

    }

    private fun setupCollectors() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { reactTo(it) }
            }
        }
    }

    private fun reactTo(mainUiState: SplashUiState) {
        mainUiState.isOnBoardingShown?.let {
            if (!it) {
                splashNavigation.apply {
                    this@SplashFragment.navigateToOnBoarding()
                }
                viewModel.saveObBoardingToDataStore()
            } else {
                splashNavigation.apply {
                    this@SplashFragment.navigateToHome()
                }
            }
        }
    }

    private fun navigateWithDeepLink(deepLink: String, @IdRes popUpTo: Int, inclusive: Boolean) {
        val request = NavDeepLinkRequest.Builder
            .fromUri(deepLink.toUri())
            .build()
        val navOptions = NavOptions.Builder()
            .setPopUpTo(popUpTo, inclusive)
            .build()
        findNavController().navigate(request, navOptions)
    }
}