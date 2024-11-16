package com.syfuzzaman.test_project_gozayaan.ui.page_details.banner

import com.syfuzzaman.test_project_gozayaan.ui.common.BaseListItemCallback
import com.syfuzzaman.test_project_gozayaan.ui.common.MyBaseAdapter
import com.syfuzzaman.test_project_gozayaan.R

class DetailImageListAdapter (
    callback: BaseListItemCallback<DetailImage>
) : MyBaseAdapter<DetailImage>(callback) {
    override fun getLayoutIdForPosition(position: Int): Int {
        return R.layout.item_detail_image
    }
}