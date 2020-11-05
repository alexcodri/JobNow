package com.ac.jobnow.utils.extensions

import android.animation.ObjectAnimator
import android.content.Context
import android.util.DisplayMetrics
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.LinearLayout
import android.widget.RelativeLayout
import androidx.core.animation.doOnStart
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.setMargins
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

fun View.changeBackground(resourceId: Int) {
    background = ResourcesCompat.getDrawable(resources, resourceId, context.theme)
}

inline fun <reified T : View> Context.inflateLayoutAs(id: Int): T {
    return View.inflate(this, id, null) as T
}


private fun getLayoutParamsForLoadingCircles() = LinearLayout.LayoutParams(
    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT,
    1.0f
)

fun LinearLayout.addLoadingCircles() {
    val view = context.inflateLayoutAs<RelativeLayout>(R.layout.loading_circle)
    val layoutParams = getLayoutParamsForLoadingCircles().apply {
        setMargins(6)
    }
    this.addView(view, layoutParams)
}

fun Window.setSquareLayout(widthPercentage: Int = -1, heightPercentage: Int = -1) {
    context?.resources?.displayMetrics?.let { displayMetrics ->

        val result = percentageToDp(widthPercentage, heightPercentage, displayMetrics)
        if (widthPercentage == -1)
            this.setLayout(result.second, result.second)
        else if (heightPercentage == -1)
            this.setLayout(result.first, result.first)
        else
            this.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
    } ?: this.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
}
