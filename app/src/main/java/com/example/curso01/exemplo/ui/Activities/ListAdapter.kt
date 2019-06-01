package com.example.curso01.exemplo.ui.Activities

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.curso01.exemplo.R
import com.example.curso01.exemplo.model.Team

class ListAdapter(var context: Context, var list: List<Team>) : BaseAdapter() {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var inflator = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        var view = inflator.inflate(R.layout.item_team, null)

        var nameTeam =  view.findViewById<TextView>(R.id.nameTeam)
        var stateTeam = view.findViewById<TextView>(R.id.nameState)
        var imgTeam = view.findViewById<ImageView>(R.id.imgTeam)

        nameTeam.text = list[position].nome
        stateTeam.text = list[position].estado

        Glide.with(context).load(list[position].escudo).into(imgTeam)

        return view
    }

    override fun getItem(position: Int): Any {
        return 0
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return list.size
    }
}