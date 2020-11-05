package com.ac.jobnow.ui.views.custom

import android.animation.ObjectAnimator
import android.graphics.drawable.TransitionDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.lifecycleScope
import com.ac.jobnow.R
import com.ac.jobnow.databinding.DialogLoadingViewBinding
import com.ac.jobnow.utils.AnimationConstants.LOADING_SCREEN_ADD_COLOR_DURATION
import com.ac.jobnow.utils.AnimationConstants.LOADING_SCREEN_DELAY
import com.ac.jobnow.utils.AnimationConstants.LOADING_SCREEN_REMOVE_COLOR_DURATION
import com.ac.jobnow.utils.AnimationConstants.LOADING_SCREEN_TRANSLATE_DURATION
import com.ac.jobnow.utils.AnimationConstants.LOADING_TRANSLATION_Y
import com.ac.jobnow.utils.ViewConstants.LOADING_DIALOG_WIDTH
import com.ac.jobnow.utils.extensions.addLoadingCircles
import com.ac.jobnow.utils.extensions.setSquareLayout
import kotlinx.android.synthetic.main.loading_circle.view.*
import kotlinx.coroutines.*

class LoadingScreenDialog : DialogFragment(),
    CoroutineScope by MainScope() {

    var _binding: DialogLoadingViewBinding? = null
    private val binding get() = _binding!!
    var startY: Float = 0.0f
    var job: Job? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setDialogWindow()
        _binding = DialogLoadingViewBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun setDialogWindow() {
        dialog?.setCanceledOnTouchOutside(true)
        dialog?.window?.setBackgroundDrawable(
            context?.let {
                ContextCompat.getDrawable(it, R.drawable.generic_dialog_background)
            })
        isCancelable = false
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        binding.tvLoadingText.text = context?.resources?.getString(R.string.default_loading_message)
        startY = binding.llLoading.getChildAt(0).image_loading_circle.y
    }

    fun setLayoutForDialogWindow() {
        dialog?.window?.setSquareLayout(widthPercentage = LOADING_DIALOG_WIDTH)
    }

    fun launchCoroutineAnimation() {
        if (job == null) {
            job = lifecycleScope.launchWhenResumed {
                animateUI()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        setLayoutForDialogWindow()
        launchCoroutineAnimation()
    }

    private suspend fun animateUI() {
        var position = 0
        while (true) {
            binding.llLoading.getChildAt(position % 4).image_loading_circle.let {
                animateCircle(it)
            }
            delay(LOADING_SCREEN_DELAY)
            position += 1
        }
    }

    private fun animateCircle(view: View) {
        val translationAnimator =
            ObjectAnimator.ofFloat(
                view, "translationY", startY, startY - LOADING_TRANSLATION_Y
            ).apply {
                duration = LOADING_SCREEN_TRANSLATE_DURATION
            }
        val colorAnimation = view.background as TransitionDrawable

        lifecycleScope.launch {
            colorAnimation.startTransition(LOADING_SCREEN_ADD_COLOR_DURATION)
            translationAnimator.start()
            delay(LOADING_SCREEN_DELAY)
            colorAnimation.reverseTransition(LOADING_SCREEN_REMOVE_COLOR_DURATION)
            translationAnimator.reverse()
        }
    }

    private fun setupUI() {
        for (i in 0..3) {
            binding.llLoading.addLoadingCircles()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onDestroy() {
        super.onDestroy()
        cancel()
    }
}