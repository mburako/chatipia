package com.burak.chatipia.ui

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.burak.chatipia.R
import com.burak.chatipia.data.local.SharedPrefUtils


class MainActivity : AppCompatActivity() {
    private lateinit var leaveButton: Button
    private lateinit var appTitle: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar!!.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar!!.setDisplayShowCustomEnabled(true)
        supportActionBar!!.setCustomView(R.layout.custom_action_bar)
        val view: View = supportActionBar!!.customView

        leaveButton = view.findViewById(R.id.leave_button)
        leaveButton.setOnClickListener {
            SharedPrefUtils.resetLoggedInUserName(this)
            setTitle(getString(R.string.app_name))
            onBackPressed()
        }

        appTitle = view.findViewById(R.id.action_bar_title)
    }

    fun setTitle(title: String) {
        if (::appTitle.isInitialized) {
            appTitle.text = title
        }
    }

    fun setVisibilityLeaveButton(gone: Boolean) {
        if (::leaveButton.isInitialized) {
           if (gone) {
               leaveButton.visibility = View.GONE
           } else {
               leaveButton.visibility = View.VISIBLE
           }
        }
    }
}
