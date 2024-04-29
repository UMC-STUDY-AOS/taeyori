package com.example.mypractice

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.mypractice.databinding.FragmentLockerBinding
import com.google.android.material.tabs.TabLayoutMediator

class LockerFragment() : Fragment() {
    lateinit var binding : FragmentLockerBinding
    private var information = arrayListOf("저장한 곡", "음악파일")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLockerBinding.inflate(inflater, container, false)

        var lockerAdapter = LockerVPAdapter(this)
        binding.lockerSongVp.adapter = lockerAdapter

        TabLayoutMediator(binding.lockerSongTb, binding.lockerSongVp){
            tab, position -> tab.text = information[position]
        }.attach()

        return binding.root
    }
}