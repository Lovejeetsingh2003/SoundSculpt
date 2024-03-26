package com.app.soundsculpt.adapter

import android.app.Activity
import android.content.Context
import android.media.MediaPlayer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.app.soundsculpt.R
import com.app.soundsculpt.entity.Data
import com.squareup.picasso.Picasso

class MusicAdapter(val context: Activity,val dataList : List<Data>) : RecyclerView.Adapter<MusicAdapter.ViewHolder>(){
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var image : ImageView =view.findViewById(R.id.ivCover)
        var play : ImageButton =view.findViewById(R.id.ivPlay)
        var pause : ImageButton =view.findViewById(R.id.ivPause)
        var musicName : TextView =view.findViewById(R.id.tvMusic)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
      var view = LayoutInflater.from(parent.context).inflate(R.layout.rv_music,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
      val data = dataList[position]
        holder.musicName.text = data.title

        val mediaPlayer = MediaPlayer.create(context,data.preview.toUri())

        Picasso.get().load(data.album.cover).into(holder.image)

        holder.play.setOnClickListener {
            mediaPlayer.start()
        }

        holder.pause.setOnClickListener {
            mediaPlayer.stop()
        }
    }
}