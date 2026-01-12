package com.ds.iptv.adapter

import android.content.Context
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
    private val list: List<Channel>,
    private val context: Context
) : RecyclerView.Adapter<ChannelAdapter.VH>() {

    class VH(v: View) : RecyclerView.ViewHolder(v) {
        val name: TextView = v.findViewById(R.id.channelName)
        val logo: ImageView = v.findViewById(R.id.channelLogo)
    }

    override fun onCreateViewHolder(p: ViewGroup, v: Int): VH {
        return VH(
            LayoutInflater.from(p.context)
                .inflate(R.layout.row_channel, p, false)
        )
    }

    override fun onBindViewHolder(h: VH, i: Int) {
        val c = list[i]
        h.name.text = c.name

        Glide.with(context)
            .load(c.logo)
            .placeholder(R.drawable.ic_tv)
            .into(h.logo)

        h.itemView.setOnClickListener {
            val intent = Intent(context, PlayerActivity::class.java)
            intent.putExtra("url", c.url)
            context.startActivity(intent)
        }
    }

    override fun getItemCount() = list.size
}
