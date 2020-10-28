package com.ac.jobnow.utils

import java.util.regex.Pattern

object AnimationConstants {
    const val SCALE_MAX = 100F
    const val GO_RIGHT = 1000F
    const val RETURN = 1F
    const val START_DELAY_SPLASHSCREEN = 600L
    const val DURATION_JOB_NOW_LOGO_LEFT = 100L
    const val DURATION_LONG = 1000L
    const val DURATION_DOT = 300L
    const val START_DELAY_DOT_ENLARGE = 1900L
    const val SIZE_DISAPPEAR = 0F
    const val DURATION_DOT_DISAPPEAR = 500L
}

object ButtonConstants {
    const val HEIGHT = 50f
    const val WIDTH_PERCENTAGE = 85
    const val TEXT_SIZE = 19f
    const val PADDING = 5
}

object RegexConstants {
    val emailPattern: Pattern = Pattern.compile(
        "(?:[a-z0-9!#\$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#\$%&'*+/=?^_`{|}~-]+)" +
                "*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|" +
                "\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]" +
                "*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4]" +
                "[0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|" +
                "[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53" +
                "-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])",
        Pattern.CASE_INSENSITIVE
    )

    val passwordPattern: Pattern = Pattern.compile(
        "^(?=.*[a-z])(?=.*[A-Z])(?=.*[[^\\w\\s]])(?=\\S+\$).{8,30}$"
    )
}

object NetworkConstants {
    const val BASE_URI = "http://joblistings.run-eu-central1.goorm.io/"
}