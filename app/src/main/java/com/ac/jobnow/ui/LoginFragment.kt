package com.ac.jobnow.ui

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.animation.doOnEnd
import androidx.core.animation.doOnStart
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import com.ac.jobnow.AnimationConstants
import com.ac.jobnow.R
import com.ac.jobnow.databinding.FragmentLoginBinding
import com.ac.jobnow.extensions.animateComponent

class LoginFragment : Fragment() {

    private var loginBinding: FragmentLoginBinding? = null
    private val binding get() = loginBinding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        loginBinding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    private fun setupUI() {
        handleDotAnimation()
        handleEmailETAnimation()
        handlePasswordETAnimation()
    }

    private fun handleEmailETAnimation() {
        binding.tilLogin.apply {
            animateComponent("scaleX")
            animateComponent("scaleX", 1F, View.VISIBLE, 700, 300)
            animateComponent("scaleY")
            animateComponent("scaleY", 1F, View.VISIBLE, 700, 300)
            animateComponent("translationY", -550F, View.VISIBLE, 700, 300)
        }
    }


    private fun handlePasswordETAnimation() {
        binding.tilPassword.apply {
            animateComponent("scaleX")
            animateComponent("scaleX", 1F, View.VISIBLE, 700, 300)
            animateComponent("scaleY")
            animateComponent("scaleY", 1F, View.VISIBLE, 700, 300)
            animateComponent("translationY", -250F, View.VISIBLE, 700, 300)
        }

    }

    private fun handleDotAnimation() {
        binding.dotLogo.apply {
            animateComponent("scaleX", AnimationConstants.SCALE_MAX)
            animateComponent("scaleY", AnimationConstants.SCALE_MAX)
            animateComponent(
                "scaleX",
                AnimationConstants.SIZE_DISAPPEAR,
                View.VISIBLE,
                AnimationConstants.DURATION_DOT,
                AnimationConstants.DURATION_DOT_DISAPPEAR
            )
            dotDisappear()
        }
    }

    private fun dotDisappear() {
        ObjectAnimator.ofFloat(binding.dotLogo, "scaleY", AnimationConstants.SIZE_DISAPPEAR).apply {
            startDelay = AnimationConstants.DURATION_DOT
            duration = AnimationConstants.DURATION_DOT_DISAPPEAR
            start()
        }.apply {
            doOnStart {
                loginBinding?.clFragment?.setBackgroundColor(
                    ResourcesCompat.getColor(
                        resources,
                        R.color.white,
                        activity?.theme
                    )
                )
            }
            doOnEnd {
                loginBinding?.dotLogo?.visibility = View.GONE
            }
        }
    }
}