package com.ac.jobnow.ui.splashscreen

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
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
        ObjectAnimator.ofFloat(binding.jobLogo, "translationX", -1300f).apply {
            duration = 100
            start()
        }
    }

    private fun animationJobGoRight() {
        ObjectAnimator.ofFloat(binding.jobLogo, "translationX", 1F).apply {
            startDelay = 600
            duration = 1000
            start()
        }
    }

    private fun animationDotGoRight() {
        ObjectAnimator.ofFloat(binding.dotLogo, "translationX", 1000f).apply {
            duration = 100
            start()
        }
    }

    private fun animationDotGoLeft() {
        ObjectAnimator.ofFloat(binding.dotLogo, "translationX", 1F).apply {
            startDelay = 600
            duration = 1000
            start()
        }
    }

    private fun animationDotEnlarge() {
        ObjectAnimator.ofFloat(binding.dotLogo, "scaleX", 100F).apply {
            startDelay = 1900
            duration = 300
            start()
        }
        ObjectAnimator.ofFloat(binding.dotLogo, "scaleY", 100F).apply {
            startDelay = 1900
            duration = 300
            start()
        }
    }
}