package com.burak.chatipia.ui.chat

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.burak.chatipia.R
import com.burak.chatipia.data.local.LocalMessages
import com.squareup.picasso.Picasso
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*


/**
 * Created by mburak on 13.04.2021.
 */
class MessagesAdapter(private var messages: MutableList<LocalMessages>): RecyclerView.Adapter<MessageItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageItemViewHolder {
        return MessageItemViewHolder.create(parent, viewType)
    }

    override fun getItemCount(): Int = messages.size

    override fun getItemViewType(position: Int): Int {
        val currentItem = messages[position]
        return if (currentItem.ownerName == currentItem.username) {
            LocalMessages.ItemType.OUT.ordinal
        } else {
            LocalMessages.ItemType.IN.ordinal
        }
    }

    override fun onBindViewHolder(holder: MessageItemViewHolder, position: Int) {
        val currentItem = messages[position]

        holder.messageContentTextView.text = currentItem.text
        setFormattedDate(holder, currentItem.timestamp)
        holder.messageUsernameTextView.text = currentItem.username
        setAvatarUrl(holder, currentItem)
    }

    fun submitList(list: List<LocalMessages>) {
        messages.clear()
        messages.addAll(list)
        notifyDataSetChanged()
    }

    private fun setAvatarUrl(holder: MessageItemViewHolder, currentItem: LocalMessages) {
        if (currentItem.avatarURL.isNullOrEmpty()) {
            Picasso.get().load(R.mipmap.ic_user_placeholder).fit().into(holder.avatarImageView);
        } else {
            Picasso.get()
                .load(currentItem.avatarURL)
                .placeholder(R.mipmap.ic_user_placeholder)
                .error(R.mipmap.ic_user_placeholder)
                .into(holder.avatarImageView)
        }
    }

    @SuppressLint("SimpleDateFormat")
    private fun setFormattedDate(holder: MessageItemViewHolder, timestamp: Long) {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = timestamp

        val pattern = "hh.mm a dd/MM/yyyy"
        val simpleDateFormat = SimpleDateFormat(pattern)
        holder.messageDateTextView.text = simpleDateFormat.format(calendar.time)
    }
}