package com.example.foodorderingapp.ui.component

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import androidx.annotation.DrawableRes
import androidx.appcompat.content.res.AppCompatResources
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
//        minHeight = 0
//        minimumHeight = 0

        if(attrs != null) {
            val attributes =  context.theme.obtainStyledAttributes(attrs, R.styleable.PreferenceButton, 0, 0)

            val leftIcon = attributes.getDrawable(R.styleable.PreferenceButton_left_icon)
            val rightIcon = attributes.getDrawable(R.styleable.PreferenceButton_right_icon)
            setCompoundDrawablesWithIntrinsicBounds(leftIcon, null, rightIcon, null)
            compoundDrawablePadding = resources.getDimension(R.dimen.button_card_icon_padding).toInt()

            attributes.recycle()
        }

        setTextColor(resources.getColor(R.color.black))
        setTextAppearance(context, R.style.preferenceButtonText)
        setBackgroundDrawable(resources.getDrawable(R.drawable.preference_button_background))
    }

    fun setLeftIcon(@DrawableRes leftIcon: Int) {
        setCompoundDrawablesWithIntrinsicBounds(AppCompatResources.getDrawable(context, leftIcon), null, null, null)
    }

    fun setRightIcon(@DrawableRes rightIcon: Int) {
        setCompoundDrawablesWithIntrinsicBounds(null, null, AppCompatResources.getDrawable(context, rightIcon), null)
    }

    fun setLeftRightIcon(@DrawableRes leftIcon: Int, @DrawableRes rightIcon: Int) {
        setCompoundDrawablesWithIntrinsicBounds(AppCompatResources.getDrawable(context, leftIcon), null, AppCompatResources.getDrawable(context, rightIcon), null)
    }
}