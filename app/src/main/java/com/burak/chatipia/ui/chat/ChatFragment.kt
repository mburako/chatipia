package com.burak.chatipia.ui.chat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.burak.chatipia.R
import com.burak.chatipia.data.AppDatabase
import com.burak.chatipia.data.ViewModelFactory
import com.burak.chatipia.data.local.LocalMessages
import com.burak.chatipia.data.local.SharedPrefUtils
import com.burak.chatipia.repository.MessagesRepository
import com.burak.chatipia.ui.MainActivity
import com.burak.chatipia.ui.MainViewModel
import kotlinx.android.synthetic.main.fragment_chat.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ChatFragment : Fragment() {
    private lateinit var viewModel: MainViewModel
    private lateinit var messagesAdapter: MessagesAdapter
    private val userName: String? by lazy { SharedPrefUtils.getLoggedInUserName(activity) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chat, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupAdapter()

        sendMessageButton.setOnClickListener {
            val text = sendMessageEditText.text.toString().trim()
            if (text.isNotEmpty()) {
                sendMessage(text)
            }
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if (userName == null) {
            activity?.onBackPressed()
            return
        }

        setupViewModel()

        viewModel.messages.observe(viewLifecycleOwner, Observer {
            messagesAdapter.submitList(it)
            messagesList.scrollToPosition(it.size - 1)
        })
    }

    override fun onResume() {
        super.onResume()
        (activity as MainActivity).setVisibilityLeaveButton(false)
    }

    private fun setupViewModel() {
        val db = AppDatabase.getDatabase(activity?.applicationContext!!)

        viewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(MessagesRepository(db.messagesDao(), userName!!))
        ).get(MainViewModel::class.java)
    }

    private fun setupAdapter() {
        messagesAdapter = MessagesAdapter(mutableListOf())
        messagesList.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = messagesAdapter
        }
    }

    private fun sendMessage(text: String) {
        val db = AppDatabase.getDatabase(activity?.applicationContext!!)

        val timestamp = System.currentTimeMillis()
        val message = LocalMessages(id = timestamp.toString(), text = text, timestamp = timestamp,
            avatarURL = "", username = userName!!, ownerName = userName!!)


        GlobalScope.launch(Dispatchers.IO) {
            db.messagesDao().insert(message)
        }
    }
}
