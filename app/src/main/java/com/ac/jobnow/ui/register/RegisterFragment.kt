package com.ac.jobnow.ui.register

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.edit
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.ac.jobnow.R
import com.ac.jobnow.databinding.FragmentRegisterBinding
import com.ac.jobnow.repository.model.registerModels.RegisterResponse
import com.ac.jobnow.repository.model.registerModels.User
import com.ac.jobnow.utils.extensions.endLoginLoadingAnimation
import com.ac.jobnow.utils.extensions.showLoadingAnimation
import com.ac.jobnow.utils.extensions.stringValue
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : Fragment() {

    private var registerBinding: FragmentRegisterBinding? = null
    private val binding get() = registerBinding!!
    private val skills: ArrayList<String> = arrayListOf()
    private val registerFragmentViewModel: RegisterFragmentViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        registerBinding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (skills.size == 0) {
            binding.flexBoxSkillsRegister.visibility = View.INVISIBLE
        }
        handleRegister()
        handleSkillAddButton()
        handleSkillET()
    }

    private fun handleRegister() {
        binding.btnRegister.setOnClickListener {
            if (handleInputs()) {
                toggleProgressDialog(true)
                binding.apply {
                    registerFragmentViewModel.register(
                        User(
                            etRegisterEmail.stringValue(),
                            etRegisterPassword.stringValue(),
                            etRegisterName.stringValue(),
                            etRegisterSurname.stringValue(),
                            etRegisterCurrentPosition.stringValue(),
                            etRegisterCurrentCompany.stringValue(),
                            skills,
                            cbRegisterRecruiter.isChecked
                        )
                    )
                }
                registerFragmentViewModel.registerResult.observe(viewLifecycleOwner, {
                    handleRegisterResponse(it)
                })
            } else {
                Snackbar.make(
                    binding.skillEtRegister,
                    "All fields must be completed!",
                    Snackbar.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun handleRegisterResponse(registerResponse: RegisterResponse) {
        toggleProgressDialog(false)
        if (registerResponse.isUniqueUser) {
            val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
            sharedPref?.edit {
                putBoolean("isLoggedIn", registerResponse.isUniqueUser)
                putBoolean("isRecruiter", registerResponse.isRecruiter)
                apply()
            }
            findNavController().navigate(R.id.dashboardFragment)
        } else {
            Snackbar.make(
                binding.skillEtRegister,
                "The account already exists!",
                Snackbar.LENGTH_SHORT
            ).show()
        }
    }

    private fun handleInputs(): Boolean {
        binding.apply {
            return (!etRegisterEmail.text.isNullOrEmpty() && !etRegisterName.text.isNullOrEmpty()
                    && !etRegisterSurname.text.isNullOrEmpty() && !etRegisterPassword.text.isNullOrEmpty()
                    && !etRegisterCurrentPosition.text.isNullOrEmpty() && skills.isNotEmpty())
        }
    }

    private fun handleSkillET() {
        binding.apply {
            skillEtRegister.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun afterTextChanged(s: Editable?) {}
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    addSkillButton.visibility = View.VISIBLE
                    if (s != null) {
                        if (s.isEmpty()) {
                            addSkillButton.visibility = View.INVISIBLE
                        }
                    }
                }
            })
        }
    }

    private fun handleSkillAddButton() {
        binding.apply {
            addSkillButton.setOnClickListener {
                addSkillTextViews(skillEtRegister.text.toString())
                skillEtRegister.text.clear()
            }
        }
    }

    private fun addSkillTextViews(skill: String) {
        binding.flexBoxSkillsRegister.visibility = View.VISIBLE

        val skillTextView = TextView(context)
        skills.add(skill)
        skillTextView.layoutParams = LinearLayout
            .LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
        (skillTextView.layoutParams as LinearLayout.LayoutParams)
            .setMargins(25, 20, 0, 20)

        skillTextView.background = getFromDrawable(context, R.color.tv_job_skills_color)
        skillTextView.setPadding(27, 27, 20, 5)
        skillTextView.text = skill
        context?.let { ContextCompat.getColor(it, R.color.white) }?.let {
            skillTextView.setTextColor(
                it
            )
        }
        skillTextView.textSize = 16F
        binding.flexBoxSkillsRegister.addView(skillTextView)

        skillTextView.setOnClickListener {
            skills.remove(skill)
            skillTextView.visibility = View.GONE
            if (skills.size == 0) {
                binding.flexBoxSkillsRegister.visibility = View.INVISIBLE
            }
        }
    }

    private fun getFromDrawable(context: Context?, resourceId: Int): Drawable? {
        return context?.let { ActivityCompat.getDrawable(it, resourceId) }
    }

    private fun toggleProgressDialog(show: Boolean) {
        activity?.runOnUiThread {
            if (show) {
                showLoadingAnimation()
            } else {
                endLoginLoadingAnimation(R.id.registerFragment)
            }
        }
    }
}