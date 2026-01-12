package com.ds.iptv

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.ui.PlayerView

class MainActivity : AppCompatActivity() {

    private lateinit var player: ExoPlayer
    private val PREFS = "iptv_prefs"
    private val KEY_URL = "playlist_url"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val playerView = PlayerView(this)
        setContentView(playerView)

        player = ExoPlayer.Builder(this).build()
        playerView.player = player

        val prefs = getSharedPreferences(PREFS, Context.MODE_PRIVATE)
        val savedUrl = prefs.getString(KEY_URL, null)

        if (savedUrl == null) {
            askForPlaylist(prefs)
        } else {
            playStream(savedUrl)
        }
    }

    private fun askForPlaylist(prefs: android.content.SharedPreferences) {
        val input = EditText(this)
        input.hint = "Paste M3U / M3U8 URL"

        AlertDialog.Builder(this)
            .setTitle("Enter your playlist")
            .setView(input)
            .setCancelable(false)
            .setPositiveButton("PLAY") { _, _ ->
                val url = input.text.toString().trim()
                if (url.isNotEmpty()) {
                    prefs.edit().putString(KEY_URL, url).apply()
                    playStream(url)
                }
            }
            .show()
    }

    private fun playStream(url: String) {
        val mediaItem = MediaItem.fromUri(url)
        player.setMediaItem(mediaItem)
        player.prepare()
        player.play()
    }

    override fun onDestroy() {
        super.onDestroy()
        player.release()
    }
}
