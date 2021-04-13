package com.burak.chatipia.ui.chat

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.burak.chatipia.R
import com.burak.chatipia.data.local.LocalMessages
import com.squareup.picasso.Picasso


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
        holder.messageDateTextView.text = currentItem.timestamp.toString()
        holder.messageUsernameTextView.text = currentItem.username
        Picasso.get()
            .load(currentItem.avatarURL)
            .placeholder(R.mipmap.ic_user_placeholder)
            .error(R.mipmap.ic_user_placeholder)
            .into(holder.avatarImageView)
    }

    fun submitList(list: List<LocalMessages>) {
        messages.clear()
        messages.addAll(list)
        notifyDataSetChanged()
    }
}