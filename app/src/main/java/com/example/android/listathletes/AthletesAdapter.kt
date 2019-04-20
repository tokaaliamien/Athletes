package com.example.android.listathletes

import android.content.Context
import android.content.Intent
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.android.listathletes.R.id.image_view
import kotlinx.android.synthetic.main.activity_details.*
import kotlinx.android.synthetic.main.item_view.view.*


/**
 * Created by Toka on 2019-04-20.
 */

class AthletesAdapter(val athletes: ArrayList<Athlete>, val context: Context) : RecyclerView.Adapter<ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_view, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder?.name.text = athletes.get(position).name
        holder?.brief.text = athletes.get(position).brief
        val imagePath = athletes.get(position).image
        if (imagePath.equals(""))
            holder?.image.visibility = View.GONE
        else {
            holder?.image.visibility = View.VISIBLE
            Glide.with(context).load(imagePath).into(holder?.image);
        }
        holder.itemView.setOnClickListener {
            val intent = Intent(context, DetailsActivity::class.java)
            intent.putExtra("athlete", athletes.get(position))
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return athletes.size
    }
}

class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val name = view.tv_name
    val image = view.image_view
    val brief = view.tv_brief
}