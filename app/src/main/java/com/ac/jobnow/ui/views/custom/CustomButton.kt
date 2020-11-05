package com.ac.jobnow.ui.views.custom


import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.res.ResourcesCompat
import com.ac.jobnow.R
import com.ac.jobnow.utils.ButtonConstants
import com.ac.jobnow.utils.extensions.setPercentageWidth

class CustomBlueButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) :
    AppCompatButton(context, attrs, defStyleAttr) {

    init {
        this.background =
            ResourcesCompat.getDrawable(resources, R.drawable.bg_custom_btn, context.theme)
        height =
            TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                ButtonConstants.HEIGHT,
                resources.displayMetrics
            )
                .toInt()
        setPercentageWidth(ButtonConstants.WIDTH_PERCENTAGE)
        gravity = Gravity.CENTER
        typeface = Typeface.create("sans-serif-medium", Typeface.NORMAL)
        setPadding(0, ButtonConstants.PADDING, 0, ButtonConstants.PADDING)
        isClickable = true
        textSize = ButtonConstants.TEXT_SIZE
    }
}
