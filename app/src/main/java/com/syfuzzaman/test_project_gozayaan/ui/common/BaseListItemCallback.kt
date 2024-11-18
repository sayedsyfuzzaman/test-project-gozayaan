package com.syfuzzaman.test_project_gozayaan.ui.common

import android.view.View

interface BaseListItemCallback<T : Any> {
    fun onItemClicked(item: T) {}
    fun onItemClicked(view: View, item: T) {}
}