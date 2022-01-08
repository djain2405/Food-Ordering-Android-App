package com.example.foodorderingapp.ui.component

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import androidx.appcompat.widget.AppCompatButton
import com.example.foodorderingapp.R

class PreferenceButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : AppCompatButton(context, attrs) {
    init {
        textAlignment = TEXT_ALIGNMENT_CENTER
        gravity = Gravity.CENTER_VERTICAL
        isAllCaps = false
        minHeight = 0
        minimumHeight = 0

        setTextColor(resources.getColor(R.color.black))
        setTextAppearance(context, R.style.preferenceButtonText)
        setBackgroundDrawable(resources.getDrawable(R.drawable.preference_button_background))
    }
}