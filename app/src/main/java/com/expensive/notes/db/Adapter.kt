package com.expensive.notes.db

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.expensive.notes.EditActivity
import com.expensive.notes.R


class Adapter(listMain: ArrayList<ListItem>, contextM: Context) : RecyclerView.Adapter<Adapter.MyHolder>() {

    var listArray = listMain
    var context = contextM
    class MyHolder(itemView: View, contextV:Context) : RecyclerView.ViewHolder(itemView) {
        val tvTitle = itemView.findViewById<TextView>(R.id.tvtitle)
        val context = contextV
        fun setData(item: ListItem) {
            tvTitle.text = item.title
            itemView.setOnClickListener{

                val intent = Intent(context,EditActivity::class.java).apply {
                    putExtra(MyIntentConstance.I_TITLE_KEY,item.title)
                    putExtra(MyIntentConstance.I_DESC_KEY,item.desc)
                    putExtra(MyIntentConstance.I_URI_KEY,item.uri)
                }
                context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val inflater = LayoutInflater.from(parent.context)
        return MyHolder(inflater.inflate(R.layout.rc_item, parent, false),context)
    }

    override fun getItemCount(): Int {
        return listArray.size
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.setData(listArray.get(position))
    }
    fun updateAdapter(listItems:List<ListItem>){
        listArray.clear()
        listArray.addAll(listItems)
        notifyDataSetChanged()
    }

}