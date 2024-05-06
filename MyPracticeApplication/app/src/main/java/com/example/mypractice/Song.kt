package com.example.mypractice

data class Song(
    val title : String = "",
    val singer : String = "",
    val second : Int = 0,
    val playTime : Int = 0,
    var isPlaying : Boolean = false
)
