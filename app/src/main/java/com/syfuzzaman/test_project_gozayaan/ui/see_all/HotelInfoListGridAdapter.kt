package com.syfuzzaman.test_project_gozayaan.ui.see_all

import com.syfuzzaman.test_project_gozayaan.R
import com.syfuzzaman.test_project_gozayaan.data.api.HotelInfo
import com.syfuzzaman.test_project_gozayaan.ui.common.BaseListItemCallback
import com.syfuzzaman.test_project_gozayaan.ui.common.MyBaseAdapter

class HotelInfoListGridAdapter(callback: BaseListItemCallback<HotelInfo>
) : MyBaseAdapter<HotelInfo>(callback) {
    override fun getLayoutIdForPosition(position: Int): Int {
        return R.layout.list_grid_item_hotel
    }
}