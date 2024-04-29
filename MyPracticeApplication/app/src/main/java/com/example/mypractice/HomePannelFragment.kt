package com.example.mypractice

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.mypractice.databinding.FragmentHomePannel1Binding
import com.example.mypractice.databinding.FragmentHomePannel2Binding

class HomePannelFragment(val fragRes : Int) : Fragment() {
    lateinit var binding1 : FragmentHomePannel1Binding
    lateinit var binding2 : FragmentHomePannel2Binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding1 = FragmentHomePannel1Binding.inflate(inflater, container, false)
        binding2 = FragmentHomePannel2Binding.inflate(inflater, container, false)

        if(fragRes == 1){
            return binding1.root
        }
        else{
            return binding2.root
        }
    }
}