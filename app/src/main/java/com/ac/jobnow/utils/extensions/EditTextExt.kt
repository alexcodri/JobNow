package com.ac.jobnow.utils.extensions

import androidx.appcompat.widget.AppCompatEditText
import java.util.regex.Pattern

fun AppCompatEditText.checkInput(pattern: Pattern): Boolean {
    return pattern.matcher(text.toString()).matches()
}