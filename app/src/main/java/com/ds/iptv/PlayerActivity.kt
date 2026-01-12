package com.ds.iptv

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ds.iptv.databinding.ActivityPlayerBinding
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem

class PlayerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPlayerBinding
    private lateinit var player: ExoPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPlayerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        player = ExoPlayer.Builder(this).build()
        binding.playerView.player = player

        val url = intent.getStringExtra("url") ?: return
        player.setMediaItem(MediaItem.fromUri(url))
        player.prepare()
        player.play()
    }

    override fun onDestroy() {
        super.onDestroy()
        player.release()
    }
}
