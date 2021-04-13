package com.burak.chatipia.ui.login

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.burak.chatipia.R
import com.burak.chatipia.data.local.SharedPrefUtils
import com.burak.chatipia.ui.MainActivity
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loginUserNameEditText.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s != null && s.length > 2) {
                    loginButton.alpha = 1f
                } else {
                    loginButton.alpha = 0.5f
                }
            }

            override fun afterTextChanged(s: Editable?) {
                // Do nothing
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // Do nothing
            }
        })

        loginButton.setOnClickListener {
            val username = loginUserNameEditText.text.toString()
            if (!username.isNullOrEmpty() && username.length > 2) {
                SharedPrefUtils.setLoggedInUserName(username, activity)
                loginUserNameEditText.text.clear()
                NavHostFragment.findNavController(this).navigate(R.id.action_to_chat)
            } else {
                Snackbar.make(view, R.string.username_length_warning_text, Snackbar.LENGTH_LONG).show()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        (activity as MainActivity).setVisibilityLeaveButton(true)
    }
}
