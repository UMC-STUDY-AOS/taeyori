package com.example.mypractice

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.mypractice.databinding.FragmentSongBinding
import com.example.mypractice.databinding.FragmentVideoBinding

class SongFragment : Fragment() {
    lateinit var binding : FragmentSongBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSongBinding.inflate(inflater, container, false)

        binding.albumSongIncludedMyTasteToggleOnIv.setOnClickListener{
            setToggleStatus(false)
        }
        binding.albumSongIncludedMyTasteToggleOffIv.setOnClickListener{
            setToggleStatus(true)
        }

        return binding.root
    }

    fun setToggleStatus(isPlaying : Boolean){
        if(isPlaying){
            binding.albumSongIncludedMyTasteToggleOnIv.visibility = View.VISIBLE
            binding.albumSongIncludedMyTasteToggleOffIv.visibility = View.GONE
        }
        else{
            binding.albumSongIncludedMyTasteToggleOnIv.visibility = View.GONE
            binding.albumSongIncludedMyTasteToggleOffIv.visibility = View.VISIBLE
        }
    }
}