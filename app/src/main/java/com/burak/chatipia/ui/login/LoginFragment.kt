package com.burak.chatipia.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.burak.chatipia.R
import com.burak.chatipia.data.local.SharedPrefUtils
import com.burak.chatipia.ui.MainActivity
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

        loginButton.setOnClickListener {
            SharedPrefUtils.setLoggedInUserName(loginUserNameEditText.text.toString(), activity)
            NavHostFragment.findNavController(this).navigate(R.id.action_to_chat)
        }
    }

    override fun onResume() {
        super.onResume()
        (activity as MainActivity).setVisibilityLeaveButton(true)
    }
}
