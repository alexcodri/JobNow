package com.ac.jobnow.ui.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ac.jobnow.R
import com.ac.jobnow.databinding.FragmentLoginBinding
import com.ac.jobnow.databinding.FragmentRegisterBinding

class RegisterFragment : Fragment() {

    private var registerBinding: FragmentRegisterBinding? = null
    private val binding get() = registerBinding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        registerBinding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }
}