package com.syfuzzaman.test_project_gozayaan.ui.landing.home.sections.category

import com.syfuzzaman.test_project_gozayaan.ui.common.BaseListItemCallback
import com.syfuzzaman.test_project_gozayaan.ui.common.MyBaseAdapter
import com.syfuzzaman.test_project_gozayaan.R

class CategoryListAdapter(
    callback: BaseListItemCallback<Category>
) : MyBaseAdapter<Category>(callback) {
    override fun getLayoutIdForPosition(position: Int): Int {
        return R.layout.list_item_category
    }
}