package com.mopanesystems.myapplication.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.mopanesystems.myapplication.Models.Options
import com.mopanesystems.myapplication.R

class CustomAdapter(private val context: Context,private val items : List<Options>) : BaseAdapter() {
    override fun getCount(): Int {
        return items.size
    }

    override fun getItem(p0: Int): Any {
        return  items[p0]
    }

    override fun getItemId(p0: Int): Long {
        return 1
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        var item = items[p0]
        var rowView: View? = null
        if (p1 == null) {
            val inflater = context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            rowView = inflater.inflate(R.layout.list_item, p2, false)
            val wardName = rowView.findViewById<TextView>(R.id.project_name)
            wardName.text=item.text
        } else {
            rowView = p1
            val wardName = rowView.findViewById<TextView>(R.id.project_name)
            wardName.text=item.text
        }
        return rowView!!
    }
}