package com.sarasmith.contacts.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sarasmith.contacts.Contact
import com.sarasmith.contacts.R

class ContactListAdapter(listen: OnButtonClickListener) : RecyclerView.Adapter<ContactListAdapter.ViewHolder>() {
    private var listener : OnButtonClickListener = listen
    private var contactList: List<Contact>? = null

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        contactList.let {

            viewHolder.nameTextView.text = it!![i].contactName
            viewHolder.phoneTextView.text = it[i].contactPhone
            viewHolder.deleteIcon.setImageResource(R.drawable.baseline_delete_24)
            viewHolder.deleteIcon.setOnClickListener {
                val data: Contact = contactList!![i]
                listener.onButtonClick(data)
            }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int):
            ViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(
            R.layout.card_layout, viewGroup, false)
        return ViewHolder(view)
    }

    fun setContactList(contacts: List<Contact>) {
        contactList = contacts
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return if (contactList == null) 0 else contactList!!.size
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var nameTextView: TextView = itemView.findViewById(R.id.contactName)
        var phoneTextView: TextView = itemView.findViewById(R.id.contactPhone)
        var deleteIcon: ImageView = itemView.findViewById(R.id.deleteButton)
    }

    interface OnButtonClickListener{
        fun onButtonClick(data : Contact)
    }
}