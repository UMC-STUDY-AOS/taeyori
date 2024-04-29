package com.example.mypractice

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.mypractice.databinding.FragmentSongFileBinding

class SongFileFragment() : Fragment() {
    lateinit var binding : FragmentSongFileBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSongFileBinding.inflate(inflater, container, false)

        return binding.root
    }
}