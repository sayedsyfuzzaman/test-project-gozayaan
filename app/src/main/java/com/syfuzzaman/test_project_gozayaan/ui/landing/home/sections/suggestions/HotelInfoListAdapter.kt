package com.syfuzzaman.test_project_gozayaan.ui.landing.home.sections.suggestions

import com.syfuzzaman.test_project_gozayaan.R
import com.syfuzzaman.test_project_gozayaan.data.api.HotelInfo
import com.syfuzzaman.test_project_gozayaan.ui.common.BaseListItemCallback
import com.syfuzzaman.test_project_gozayaan.ui.common.MyBaseAdapter

class HotelInfoListAdapter(callback: BaseListItemCallback<HotelInfo>
) : MyBaseAdapter<HotelInfo>(callback) {
    override fun getLayoutIdForPosition(position: Int): Int {
        return R.layout.list_item_hotel_large
    }
}