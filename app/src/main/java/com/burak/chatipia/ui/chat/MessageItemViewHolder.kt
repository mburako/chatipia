package com.burak.chatipia.ui.chat

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.burak.chatipia.R
import com.burak.chatipia.data.local.LocalMessages


/**
 * Created by mburak on 13.04.2021.
 */
class MessageItemViewHolder(itemView: View, itemType: Int) : RecyclerView.ViewHolder(itemView) {
    val avatarImageView = itemView.findViewById<ImageView>(R.id.avatarImageView)
    val messageUsernameTextView = itemView.findViewById<TextView>(R.id.messageUsernameTextView)
    val messageContentTextView = itemView.findViewById<TextView>(R.id.messageContentTextView)
    val messageDateTextView = itemView.findViewById<TextView>(R.id.messageDateTextView)

    companion object {
        fun create(parent: ViewGroup, itemType: Int): MessageItemViewHolder {
            return if (itemType == LocalMessages.ItemType.IN.ordinal) {
                MessageItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.message_item_in, parent, false), itemType)
            } else {
                MessageItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.message_item_out, parent, false), itemType)
            }
        }
    }
}