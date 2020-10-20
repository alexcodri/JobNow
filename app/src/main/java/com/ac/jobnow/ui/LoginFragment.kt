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
import com.ac.jobnow.R
import com.ac.jobnow.databinding.FragmentLoginBinding

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
        handleDot()
    }

    private fun handleDot() {
        dotEnlarge()
        dotDisappear()
    }

    private fun dotEnlarge() {
        ObjectAnimator.ofFloat(binding.dotLogo, "scaleX", 100F).apply {
            start()
        }

        ObjectAnimator.ofFloat(binding.dotLogo, "scaleY", 100F).apply {
            start()
        }
    }

    private fun dotDisappear() {
        ObjectAnimator.ofFloat(binding.dotLogo, "scaleX", 0F).apply {
            startDelay = 300
            duration = 700
            start()
        }
        ObjectAnimator.ofFloat(binding.dotLogo, "scaleY", 0F).apply {
            startDelay = 300
            duration = 700
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