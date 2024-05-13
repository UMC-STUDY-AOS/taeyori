package com.example.mypractice

import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import androidx.appcompat.app.AppCompatActivity
import com.example.mypractice.databinding.ActivitySongBinding
import com.google.gson.Gson

class SongActivity : AppCompatActivity() {
    lateinit var binding : ActivitySongBinding
    lateinit var song : Song
    lateinit var timer: Timer
    private var mediaPlayer: MediaPlayer? = null
    private var gson: Gson = Gson()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySongBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initSong()
        setPlayer(song)

        binding.songActivityHomeExitIvBtn.setOnClickListener {
            finish()
        }

        binding.playbarPlayIv.setOnClickListener {
            setPlayerStatus(true)
        }
        binding.playbarPauseIv.setOnClickListener {
            setPlayerStatus(false)
        }
//        binding.songProgressbarSb.setOnSeekBarChangeListener(object :
//            SeekBar.OnSeekBarChangeListener {
//            var millsAtStartTracking: Float = 0.0f
//            var secondsAtStartTracking: Int = 0
//
//            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
//                if (fromUser) {
//                    val newSecond = (progress * song.playTime) / seekBar!!.max
//                    runOnUiThread {
//                        binding.songStartTimeTv.text = formatTime(newSecond)
//                    }
//                }
//            }
//
//            override fun onStartTrackingTouch(seekBar: SeekBar?) {
//                seekBar?.let {
//                    millsAtStartTracking = timer.mills
//                    secondsAtStartTracking = (it.progress * song.playTime) / it.max
//                    timer.stop()
//                }
//            }
//
//            override fun onStopTrackingTouch(seekBar: SeekBar?) {
//                seekBar?.let {
//                    val newMills = (secondsAtStartTracking * 1000).toFloat()
//                    runOnUiThread {
//                        timer.mills = newMills
//                        it.progress = secondsAtStartTracking * 1000 / song.playTime
//                    }
//                    timer.start()
//                }
//            }
//        })
    }

    private fun formatTime(seconds: Int): String {
        val minutes = seconds / 60
        val remainingSeconds = seconds % 60
        return String.format("%02d:%02d", minutes, remainingSeconds)
    }

    private fun startTimer() {
        timer = Timer(song.playTime, song.isPlaying)
        timer.start()
    }

    override fun onPause() {
        super.onPause()
        setPlayerStatus(false)
        song.second = (binding.songProgressbarSb.progress * song.playTime)/1000
        // sharedPreference stores simple data(login & logout etc.) to the storages inside
        // and allow users to use afterwards
        val sharedPreferences = getSharedPreferences("song", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val songJson = gson.toJson(song)
        editor.putString("songData", songJson)

        editor.apply() // saves data to the storage
    }

    override fun onDestroy() {
        super.onDestroy()
        timer.interrupt()
        mediaPlayer?.release()
        mediaPlayer = null
    }

    private fun initSong() {
        if (intent.hasExtra("title") && intent.hasExtra("singer")) {
            song = Song(
                intent.getStringExtra("title")!!,
                intent.getStringExtra("singer")!!,
                intent.getIntExtra("second", 0),
                intent.getIntExtra("playTime", 0),
                intent.getBooleanExtra("isPlaying", false),
                intent.getStringExtra("music")!!
            )
        }
        startTimer()
    }

    private fun setPlayer(song : Song) {
        binding.songActivityHomeTitleTv.text = song.title
        binding.songActivityHomeSingerTv.text = song.singer
        binding.songStartTimeTv.text = String.format("%02d:%02d", song.second / 60, song.second % 60)
        binding.songEndTimeTv.text = String.format("%02d:%02d", song.playTime / 60, song.playTime % 60)
        binding.songProgressbarSb.progress = (song.second * 1000 / song.playTime)
        val music = resources.getIdentifier(song.music, "raw", this.packageName)
        mediaPlayer = MediaPlayer.create(this, music)
        setPlayerStatus(song.isPlaying)
    }

    private fun setPlayerStatus(isPlaying : Boolean){
        if(isPlaying){
            binding.playbarPlayIv.visibility = View.GONE
            binding.playbarPauseIv.visibility = View.VISIBLE
            mediaPlayer?.start()
        }else{
            binding.playbarPlayIv.visibility = View.VISIBLE
            binding.playbarPauseIv.visibility = View.GONE
            if(mediaPlayer?.isPlaying == true)
                mediaPlayer?.pause()
        }
        timer.isPlaying = isPlaying
        song.isPlaying = isPlaying
    }

    inner class Timer(private val playTime: Int, var isPlaying: Boolean): Thread(){
        private var second: Int = 0
        var mills: Float = 0f

        override fun run() {
            super.run()

            try {
                while (second < playTime) {
                    if (isPlaying) {
                        sleep(50)
                        mills += 50
                        runOnUiThread {
                            binding.songProgressbarSb.progress = (mills / playTime).toInt()
                        }

                        if (mills % 1000 == 0f) {
                            second++
                            runOnUiThread {
                                binding.songStartTimeTv.text =
                                    String.format("%02d:%02d", second / 60, second % 60)
                            }
                        }
                    }
                }
            }catch (e: InterruptedException){
                Log.d("Song","쓰레드가 죽었습니다. ${e.message}")
            }
        }
    }
}


