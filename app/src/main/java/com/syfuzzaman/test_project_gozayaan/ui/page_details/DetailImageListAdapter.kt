package com.syfuzzaman.test_project_gozayaan.ui.page_details

import com.syfuzzaman.test_project_gozayaan.ui.common.MyBaseAdapter
import com.syfuzzaman.test_project_gozayaan.R

class DetailImageListAdapter () : MyBaseAdapter<String>() {
    override fun getLayoutIdForPosition(position: Int): Int {
        return R.layout.item_detail_image
    }
}