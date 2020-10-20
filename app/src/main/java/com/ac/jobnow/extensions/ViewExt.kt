package com.ac.jobnow.extensions

import android.animation.ObjectAnimator
import android.view.View
import androidx.core.animation.doOnStart

fun View.animateComponent(
    propertyName: String,
    animationValue: Float = 0F,
    visibility: Int = 0,
    startDelay: Long = 0,
    duration: Long = 0,

    ) {
    ObjectAnimator.ofFloat(this, propertyName, animationValue).apply {
        this.startDelay = startDelay
        this.duration = duration
        start()
    }.doOnStart {
        this.visibility = visibility
    }
}