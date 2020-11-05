package com.ac.jobnow.ui.splashscreen

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.animation.doOnEnd
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.ac.jobnow.utils.AnimationConstants
import com.ac.jobnow.R
import com.ac.jobnow.databinding.FragmentSplashscreenBinding
import com.ac.jobnow.utils.extensions.animateComponent

class SplashscreenFragment : Fragment() {

    private var splashscreenBinding: FragmentSplashscreenBinding? = null
    private val binding get() = splashscreenBinding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        splashscreenBinding = FragmentSplashscreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    private fun setupUI() {
        dotLogoAnimations()
    }

    private fun dotLogoAnimations() {
        binding.dotLogo.apply {
            animateComponent(
                "translationX",
                AnimationConstants.GO_RIGHT,
                View.INVISIBLE,
                0,
                AnimationConstants.DURATION_JOB_NOW_LOGO_LEFT
            )
            animateComponent(
                "translationX",
                AnimationConstants.RETURN,
                View.VISIBLE,
                AnimationConstants.START_DELAY_SPLASHSCREEN,
                AnimationConstants.DURATION_LONG
            )
            animateComponent(
                "scaleX",
                AnimationConstants.SCALE_MAX,
                View.VISIBLE,
                AnimationConstants.START_DELAY_DOT_ENLARGE,
                AnimationConstants.DURATION_DOT
            )
            animationDotEnlarge()
        }
    }


    private fun animationDotEnlarge() {
        ObjectAnimator.ofFloat(binding.dotLogo, "scaleY", AnimationConstants.SCALE_MAX).apply {
            startDelay = AnimationConstants.START_DELAY_DOT_ENLARGE
            duration = AnimationConstants.DURATION_DOT
            start()
        }.doOnEnd {
            navigateToLogin()
        }
    }

    private fun navigateToLogin() {
        findNavController().navigate(R.id.action_splashscreenFragment_to_loginFragment)
    }
}