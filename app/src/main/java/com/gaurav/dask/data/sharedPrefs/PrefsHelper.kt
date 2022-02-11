package com.gaurav.dask.data.sharedPrefs

import android.content.Context
import androidx.preference.PreferenceManager.getDefaultSharedPreferences
import com.gaurav.dask.data.sharedPrefs.PrefKeys.COOKIE_ID
import com.gaurav.dask.data.sharedPrefs.PrefKeys.DEVICE_ID
import com.gaurav.dask.data.sharedPrefs.PrefKeys.IS_LOGIN
import com.gaurav.dask.data.sharedPrefs.PrefKeys.PASSWORD
import com.gaurav.dask.data.sharedPrefs.PrefKeys.SERVER_ADDRESS
import com.gaurav.dask.data.sharedPrefs.PrefKeys.USER_ID
import com.gaurav.dask.data.sharedPrefs.PrefKeys.USER_NAME

class PrefsHelper(context: Context) {
    /* Init sharedPreferences with injected context*/
    val sharedPref = getDefaultSharedPreferences(context)
    var userID by PrefsIntDelegate(USER_ID)
    var cookieId by PrefsStringDelegate(COOKIE_ID)
    var serverAddress by PrefsStringDelegate(SERVER_ADDRESS)
    var isLogin by PrefsBooleanDelegate(IS_LOGIN)
    var login by PrefsStringDelegate(USER_NAME)
    var password by PrefsStringDelegate(PASSWORD)
    var deviceId by  PrefsStringDelegate(DEVICE_ID)
}

object PrefKeys {
    const val USER_ID = "USER_ID"
    const val COOKIE_ID="cookie_id"
    const val SERVER_ADDRESS="server_address"
    const val IS_LOGIN="is_login"
    const val USER_NAME="user_name"
    const val PASSWORD="pass_word"
    const val DEVICE_ID="device_id"
}
