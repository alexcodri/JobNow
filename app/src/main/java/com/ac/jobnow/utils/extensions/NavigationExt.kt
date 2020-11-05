package com.ac.jobnow.utils.extensions

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.ac.jobnow.R
import com.ac.jobnow.utils.ViewConstants.LOADING_DIALOG_TEXT

fun Fragment.showLoadingAnimation(text: String = resources.getString(R.string.default_loading_message)) {
    findNavController().navigate(
        R.id.loadingScreenDialog,
        Bundle().apply {
            putString(
                LOADING_DIALOG_TEXT, text
            )
        })
}

fun Fragment.endLoginLoadingAnimation() {
    findNavController().navigate(R.id.loginFragment)
    findNavController().popBackStack()
}