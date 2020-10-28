package com.ac.jobnow.utils.extensions

import android.animation.ObjectAnimator
import android.util.DisplayMetrics
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.animation.doOnStart
import androidx.core.content.res.ResourcesCompat
import com.ac.jobnow.R

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

private fun percentageToDp(
    widthPercentage: Int, heightPercentage: Int, displayMetrics: DisplayMetrics
): Pair<Int, Int> {
    val xDP = (displayMetrics.widthPixels)
    val yDp = (displayMetrics.heightPixels)

    val width =
        if (widthPercentage <= 0) ViewGroup.LayoutParams.WRAP_CONTENT else xDP * widthPercentage / 100
    val height =
        if (heightPercentage <= 0) ViewGroup.LayoutParams.WRAP_CONTENT else yDp * heightPercentage / 100

    return Pair(width, height)
}

fun Button.setPercentageWidth(widthPercentage: Int) {
    context?.resources?.displayMetrics?.let {
        val result = percentageToDp(widthPercentage, 0, it)
        this.width = result.first
    }
}

fun View.changeBackground(resourceId: Int){
    background = ResourcesCompat.getDrawable(resources, resourceId, context.theme)
}