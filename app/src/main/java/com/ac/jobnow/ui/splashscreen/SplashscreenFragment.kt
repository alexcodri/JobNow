package com.ac.jobnow.ui.splashscreen

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.animation.doOnEnd
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.ac.jobnow.AnimationConstants
import com.ac.jobnow.R
import com.ac.jobnow.databinding.FragmentSplashscreenBinding

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
        setupJobAnimation()
    }

    private fun setupJobAnimation() {
        jobLogoAnimations()
        nowLogoAnimations()
    }

    private fun jobLogoAnimations() {
        animationJobGoLeft()
        animationJobGoRight()
    }

    private fun nowLogoAnimations() {
        animationDotGoRight()
        animationDotGoLeft()
        animationDotEnlarge()
    }

    private fun animationJobGoLeft() {
        ObjectAnimator.ofFloat(binding.jobLogo, "translationX", AnimationConstants.GO_LEFT).apply {
            duration = AnimationConstants.DURATION_JOB_NOW_LOGO_LEFT
            start()
        }
    }

    private fun animationJobGoRight() {
        ObjectAnimator.ofFloat(binding.jobLogo, "translationX", AnimationConstants.RETURN).apply {
            startDelay = AnimationConstants.START_DELAY_SPLASHSCREEN
            duration = AnimationConstants.DURATION_LONG
            start()
        }
    }

    private fun animationDotGoRight() {
        ObjectAnimator.ofFloat(binding.dotLogo, "translationX", AnimationConstants.GO_RIGHT).apply {
            duration = AnimationConstants.DURATION_JOB_NOW_LOGO_LEFT
            start()
        }
    }

    private fun animationDotGoLeft() {
        ObjectAnimator.ofFloat(binding.dotLogo, "translationX", AnimationConstants.RETURN).apply {
            startDelay = AnimationConstants.START_DELAY_SPLASHSCREEN
            duration = AnimationConstants.DURATION_LONG
            start()
        }
    }

    private fun animationDotEnlarge() {
        ObjectAnimator.ofFloat(binding.dotLogo, "scaleX", AnimationConstants.SCALE_MAX).apply {
            startDelay = AnimationConstants.START_DELAY_DOT_ENLARGE
            duration = AnimationConstants.DURATION_DOT
            start()
        }
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