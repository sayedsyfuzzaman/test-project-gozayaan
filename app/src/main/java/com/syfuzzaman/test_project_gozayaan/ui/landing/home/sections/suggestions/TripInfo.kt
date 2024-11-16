package com.syfuzzaman.test_project_gozayaan.ui.landing.home.sections.suggestions

data class TripInfo(
    val id: Int,
    val name: String,
    val location: String,
    val isBookmarked: Boolean = false,
)
