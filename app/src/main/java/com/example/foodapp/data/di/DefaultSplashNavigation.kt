package com.example.foodapp.data.di

import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.common.domain.model.HOME_DEEPLINK
import com.example.common.domain.model.ONBOARDING_DEEPLINK
import com.example.splash.presentation.SplashNavigation
import javax.inject.Inject

class DefaultSplashNavigation @Inject constructor() : SplashNavigation {
    override fun Fragment.navigateToOnBoarding() {
        val deepLink = NavDeepLinkRequest.Builder
            .fromUri(ONBOARDING_DEEPLINK.toUri())
            .build()
        val navOptions = NavOptions.Builder()
            .build()

        findNavController().navigate(deepLink, navOptions)
    }

    override fun Fragment.navigateToHome() {
        val deepLink = NavDeepLinkRequest.Builder
            .fromUri(HOME_DEEPLINK.toUri())
            .build()
        val navOptions = NavOptions.Builder()
            .setPopUpTo(com.example.onboarding.R.id.onboarding_naviggation, true)
            .build()

        findNavController().navigate(deepLink, navOptions)
    }

}