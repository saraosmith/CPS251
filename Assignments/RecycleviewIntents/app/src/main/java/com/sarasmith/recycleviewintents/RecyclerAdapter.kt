package com.sarasmith.recycleviewintents

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class RecyclerAdapter(private val Titles: List<Int>, private val Details : List<Int>, private val Images : List<Int>) : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val v = LayoutInflater.from(viewGroup.context).inflate(R.layout.card_layout, viewGroup, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return Data.titles.size

    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        viewHolder.itemTitle.text = Data.titles[Titles[i]]
        viewHolder.itemDetail.text = Data.details[Details[i]]
        viewHolder.itemImage.setImageResource(Data.images[Images[i]])
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var itemImage: ImageView
        var itemTitle: TextView
        var itemDetail: TextView

        init {
            itemImage = itemView.findViewById(R.id.itemImage)
            itemTitle = itemView.findViewById(R.id.itemTitle)
            itemDetail = itemView.findViewById(R.id.itemDetail)

            itemView.setOnClickListener { v: View ->
                val i = Intent(v.context,SecondActivity::class.java)

                i.putExtra("Title", Titles[adapterPosition])
                i.putExtra("Detail", Details[adapterPosition])
                i.putExtra("Image", Images[adapterPosition])

                ContextCompat.startActivity(v.context, i, null)

            }
        }
    }
}
