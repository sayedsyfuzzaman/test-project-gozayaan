package com.syfuzzaman.test_project_gozayaan.ui.landing.home.sections.suggestions

import com.syfuzzaman.test_project_gozayaan.R
import com.syfuzzaman.test_project_gozayaan.ui.common.BaseListItemCallback
import com.syfuzzaman.test_project_gozayaan.ui.common.MyBaseAdapter

class TripInfoListAdapter( callback: BaseListItemCallback<TripInfo>
) : MyBaseAdapter<TripInfo>(callback) {
    override fun getLayoutIdForPosition(position: Int): Int {
        return R.layout.list_item_trip_large
    }
}