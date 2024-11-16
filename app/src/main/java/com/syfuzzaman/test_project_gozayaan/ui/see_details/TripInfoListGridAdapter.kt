package com.syfuzzaman.test_project_gozayaan.ui.see_details

import com.syfuzzaman.test_project_gozayaan.R
import com.syfuzzaman.test_project_gozayaan.ui.common.BaseListItemCallback
import com.syfuzzaman.test_project_gozayaan.ui.common.MyBaseAdapter
import com.syfuzzaman.test_project_gozayaan.ui.landing.home.sections.suggestions.TripInfo

class TripInfoListGridAdapter(callback: BaseListItemCallback<TripInfo>
) : MyBaseAdapter<TripInfo>(callback) {
    override fun getLayoutIdForPosition(position: Int): Int {
        return R.layout.list_grid_item_trip
    }
}