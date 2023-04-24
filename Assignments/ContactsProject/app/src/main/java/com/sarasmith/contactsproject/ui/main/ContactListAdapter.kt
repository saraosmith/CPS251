package com.sarasmith.contactsproject.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sarasmith.contactsproject.Contact
import com.sarasmith.contactsproject.R
class ContactListAdapter (private val contactItemLayout: Int) : RecyclerView.Adapter<ContactListAdapter.ViewHolder>(){

    private var contactList: List<Contact>? = null

    override fun onBindViewHolder(holder: ViewHolder, listPosition: Int) {
        val name = holder.name
        val phone = holder.phone
        contactList.let {
            name.text = it!![listPosition].contactName
            phone.text = it!![listPosition].phoneNumber.toString()
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            contactItemLayout, parent, false)
        return ViewHolder(view)
    }

    fun setContactList(contacts: List<Contact>) {
        contactList = contacts
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return if (contactList == null) 0 else contactList!!.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val phone: TextView = itemView.findViewById(R.id.itemTitle)
        var name: TextView = itemView.findViewById(R.id.itemDetail)
    }
}
