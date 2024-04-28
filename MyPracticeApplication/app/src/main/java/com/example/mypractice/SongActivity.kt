package com.example.mypractice

import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.mypractice.databinding.ActivitySongBinding
import java.util.zip.Inflater


class SongActivity : AppCompatActivity() {
    lateinit var binding : ActivitySongBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySongBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.songActivityHomeExitIvBtn.setOnClickListener{
            finish()
        }

        binding.playbarPlayIv.setOnClickListener{
            setPlayerStatus(false)
        }
        binding.playbarPauseIv.setOnClickListener{
            setPlayerStatus(true)
        }

        if(intent.hasExtra("title") && intent.hasExtra("singer")){
            binding.songActivityHomeTitleTv.text = intent.getStringExtra("title")
            binding.songActivityHomeSingerTv.text = intent.getStringExtra("singer")

        }
    }

    fun setPlayerStatus(isPlaying : Boolean){
        if(isPlaying){
            binding.playbarPlayIv.visibility = View.VISIBLE
            binding.playbarPauseIv.visibility = View.GONE
        }else{
            binding.playbarPlayIv.visibility = View.GONE
            binding.playbarPauseIv.visibility = View.VISIBLE
        }
    }
}


