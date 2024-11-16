package com.syfuzzaman.test_project_gozayaan.ui.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load
import javax.inject.Singleton

@Singleton
class BindingUtil {
    companion object {
        @JvmStatic
        @BindingAdapter("loadImageResource")
        fun loadImageFromResource(view: ImageView, image: Int) {
            view.load(image)
        }
    }
}