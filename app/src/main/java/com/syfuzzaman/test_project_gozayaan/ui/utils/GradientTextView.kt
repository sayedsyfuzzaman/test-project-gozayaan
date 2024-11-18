package com.syfuzzaman.test_project_gozayaan.ui.utils

import android.content.Context
import android.graphics.LinearGradient
import android.graphics.Shader
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import com.syfuzzaman.test_project_gozayaan.R

class GradientTextView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AppCompatTextView(context, attrs, defStyleAttr) {

    private var gradient: LinearGradient? = null

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)

        // Create the gradient only if it hasn't been created yet or if the view's size has changed
        if (changed || gradient == null) {
            gradient = LinearGradient(
                0f, 0f, width.toFloat(), height.toFloat(),
                ContextCompat.getColor(context, R.color.gradient_color_start),
                ContextCompat.getColor(context, R.color.gradient_color_end),
                Shader.TileMode.CLAMP
            )
            paint.shader = gradient
        }
    }
}
