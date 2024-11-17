package com.syfuzzaman.test_project_gozayaan.ui.utils

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.navOptions


fun NavController.navigateTo(@IdRes resId: Int, args: Bundle? = null, navOptions: NavOptions? = null) {
    this.navigate(resId, args, navOptions ?: navOptions {
        launchSingleTop = true
    })
}

fun NavController.navigatePopUpTo(
    @IdRes resId: Int,
    args: Bundle? = null,
    inclusive: Boolean? = true,
    @IdRes popUpTo: Int? = null,
    navOptions: NavOptions? = null,
) {
    this.navigate(resId, args, navOptions ?: navOptions {
        launchSingleTop = true
        popUpTo(popUpTo ?: resId) {
            inclusive?.let {
                this.inclusive = inclusive
            }
        }
    })
}