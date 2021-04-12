package com.burak.chatipia.data.local

import android.app.Activity
import android.content.Context


/**
 * Created by mburak on 13.04.2021.
 */
class SharedPrefUtils {
    companion object {
        private const val PREF_NAME_LOGGED_IN_USERNAME = "pref_name_logged_in_username"

        fun setLoggedInUserName(username: String?, activity: Activity?) {
            val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE) ?: return
            with (sharedPref.edit()) {
                putString(PREF_NAME_LOGGED_IN_USERNAME, username)
                apply()
            }
        }

        fun getLoggedInUserName(activity: Activity?): String? {
            val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE) ?: return null
            return sharedPref.getString(PREF_NAME_LOGGED_IN_USERNAME, null)
        }

        fun resetLoggedInUserName(activity: Activity?) {
            setLoggedInUserName(null, activity)
        }
    }
}