package com.example.curso01.exemplo.ui.activities.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.curso01.exemplo.R
import com.example.curso01.exemplo.model.Memes

class ListAdapter(var context: Context, var list: List<Memes>) : BaseAdapter() {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var inflator = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        var view = inflator.inflate(R.layout.item_meme, null)

        var nameMeme = view.findViewById<TextView>(R.id.nameMeme)
        var imgMeme = view.findViewById<ImageView>(R.id.imgMeme)

        nameMeme.text = list[position].name

        Glide.with(context).load(list[position].url).into(imgMeme)

        return view
    }

    override fun getItem(position: Int): Any {
        return list[position]
    }

    override fun getItemId(position: Int): Long {
        return list[position].id.toLong()
    }

    override fun getCount(): Int {
        return list.size
    }
}