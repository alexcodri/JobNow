package com.ac.jobnow.utils.extensions

import android.widget.EditText
import androidx.appcompat.widget.AppCompatEditText
import java.util.regex.Pattern

fun AppCompatEditText.checkInput(pattern: Pattern): Boolean {
    return pattern.matcher(text.toString()).matches()
}

fun EditText.stringValue(): String {
    return this.text.toString()
}