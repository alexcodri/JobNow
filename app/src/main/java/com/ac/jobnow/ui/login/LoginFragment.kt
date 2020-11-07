package com.ac.jobnow.ui.login

import android.animation.ObjectAnimator
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.animation.doOnEnd
import androidx.core.animation.doOnStart
import androidx.core.content.edit
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import com.ac.jobnow.R
import com.ac.jobnow.databinding.FragmentLoginBinding
import com.ac.jobnow.repository.model.loginModels.LoginRequest
import com.ac.jobnow.utils.AnimationConstants
import com.ac.jobnow.utils.RegexConstants
import com.ac.jobnow.utils.extensions.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private var loginBinding: FragmentLoginBinding? = null
    private val binding get() = loginBinding!!
    private val loginFragmentViewModel by activityViewModels<LoginFragmentViewModel>()

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
        goToRegister()
        loginUser()
    }

    private fun setupUI() {
        //Animations
        handleDotAnimation()
        handleJobLogoAnimation()
        handleEmailETAnimation()
        handleEmailErrorAnimation()
        handlePasswordETAnimation()
        handlePasswordErrorAnimation()
        handleButtonLoginAnimation()
        handleButtonRegisterAnimation()

        //UI
        handleEmailInput()
        handlePasswordInput()
    }

    //region Animations
    private fun handleJobLogoAnimation() {
        binding.jobLogo.apply {
            animateComponent("scaleX")
            animateComponent("scaleX", 1F, View.VISIBLE, 700, 300)
            animateComponent("scaleY")
            animateComponent("scaleY", 1F, View.VISIBLE, 700, 300)
        }
    }

    private fun handleButtonRegisterAnimation() {
        binding.btnRegister.apply {
            animateComponent("scaleX")
            animateComponent("scaleX", 1F, View.VISIBLE, 700, 300)
            animateComponent("scaleY")
            animateComponent("scaleY", 1F, View.VISIBLE, 700, 300)
        }.also {
            it.setBackgroundColor(
                ResourcesCompat.getColor(
                    resources,
                    R.color.midnight_blue,
                    activity?.theme
                )
            )
        }
    }

    private fun handleButtonLoginAnimation() {
        binding.btnLogin.apply {
            animateComponent("scaleX")
            animateComponent("scaleX", 1F, View.VISIBLE, 700, 300)
            animateComponent("scaleY")
            animateComponent("scaleY", 1F, View.VISIBLE, 700, 300)
            animateComponent("translationY", 1350F, View.VISIBLE, 700, 300)

        }
    }

    private fun handleEmailETAnimation() {
        binding.tilLogin.apply {
            animateComponent("scaleX")
            animateComponent("scaleX", 1F, View.VISIBLE, 700, 300)
            animateComponent("scaleY")
            animateComponent("scaleY", 1F, View.VISIBLE, 700, 300)
            animateComponent("translationY", 450F, View.VISIBLE, 700, 300)
        }
    }

    private fun handleEmailErrorAnimation() {
        binding.tvEmailError.apply {
            animateComponent("scaleX")
            animateComponent("scaleX", 1F, View.INVISIBLE, 700, 300)
            animateComponent("scaleY")
            animateComponent("scaleY", 1F, View.INVISIBLE, 700, 300)
            animateComponent("translationY", 650F, View.INVISIBLE, 700, 300)
        }
    }

    private fun handlePasswordETAnimation() {
        binding.tilPassword.apply {
            animateComponent("scaleX")
            animateComponent("scaleX", 1F, View.VISIBLE, 700, 300)
            animateComponent("scaleY")
            animateComponent("scaleY", 1F, View.VISIBLE, 700, 300)
            animateComponent("translationY", 750F, View.VISIBLE, 700, 300)
        }
    }

    private fun handlePasswordErrorAnimation() {
        binding.tvPasswordError.apply {
            animateComponent("scaleX")
            animateComponent("scaleX", 1F, View.INVISIBLE, 700, 300)
            animateComponent("scaleY")
            animateComponent("scaleY", 1F, View.INVISIBLE, 700, 300)
            animateComponent("translationY", 950F, View.INVISIBLE, 700, 300)
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
    //endregion

    //region Inputs
    private fun handleEmailInput() {
        binding.apply {
            etLogin.setOnFocusChangeListener { _, _ ->
                if (etLogin.text?.length!! > 3 && !etLogin.checkInput(RegexConstants.emailPattern)) {
                    tilLogin.changeBackground(R.drawable.bg_custom_error)
                    tvEmailError.visibility = View.VISIBLE
                } else {
                    tilLogin.changeBackground(R.drawable.bg_edit_text)
                    tvEmailError.visibility = View.INVISIBLE
                }
            }
        }
    }

    private fun handlePasswordInput() {
        binding.apply {
            etPassword.setOnFocusChangeListener { _, _ ->
                if (etPassword.text?.length!! > 3 && !etPassword.checkInput(RegexConstants.passwordPattern)) {
                    tilPassword.changeBackground(R.drawable.bg_custom_error)
                    tvPasswordError.visibility = View.VISIBLE
                } else {
                    tilPassword.changeBackground(R.drawable.bg_edit_text)
                    tvPasswordError.visibility = View.INVISIBLE
                }
            }
        }
    }
    //endregion

    //region Button Logic
    private fun goToRegister() {
        binding.btnRegister.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }
    }

    private fun loginUser() {
        binding.btnLogin.setOnClickListener {
            toggleProgressDialog(true)
            loginFragmentViewModel.apply {
                login(
                    LoginRequest(
                        binding.etLogin.text.toString(),
                        binding.etPassword.text.toString()
                    )
                )
                loginResult.observe(viewLifecycleOwner, {
                    if (it.isUserFound) {
                        toggleProgressDialog(false)
                        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
                        sharedPref?.edit {
                            putBoolean("isLoggedIn", it.isUserFound)
                            putBoolean("isRecruiter", it.isUserRecruiter)
                            apply()
                        }
                        findNavController().navigate(R.id.dashboardFragment)
                    } else {
                        toggleProgressDialog(false)
                        Toast.makeText(
                            context,
                            "Wrong credentials or the account does not exist!",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                })
            }
        }
    }

    private fun toggleProgressDialog(show: Boolean) {
        activity?.runOnUiThread {
            if (show) {
                showLoadingAnimation()
            } else {
                endLoginLoadingAnimation(R.id.loginFragment)
            }
        }
    }
}