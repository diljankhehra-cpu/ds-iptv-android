package com.ds.iptv.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ds.iptv.PlayerActivity
import com.ds.iptv.R
import com.ds.iptv.model.Channel

class ChannelAdapter(
    private val list: List<Channel>
) : RecyclerView.Adapter<ChannelAdapter.VH>() {

    inner class VH(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.txtName)
        val logo: ImageView = view.findViewById(R.id.imgLogo)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_channel, parent, false)
        return VH(v)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val channel = list[position]

        holder.name.text = channel.name

        Glide.with(holder.itemView.context)
            .load(channel.logo)
            .into(holder.logo)

        holder.itemView.setOnClickListener {
            val i = Intent(holder.itemView.context, PlayerActivity::class.java)
            i.putExtra("url", channel.url)
            holder.itemView.context.startActivity(i)
        }
    }

    override fun getItemCount() = list.size
}
