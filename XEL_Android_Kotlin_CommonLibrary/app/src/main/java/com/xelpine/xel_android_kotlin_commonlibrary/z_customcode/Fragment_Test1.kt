package com.xelpine.xel_android_kotlin_commonlibrary.z_customcode

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.xelpine.xel_android_kotlin_commonlibrary.R


class Fragment_Test1 : Fragment()
{
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //fragment_two 가져오기
        val view: View = inflater.inflate(R.layout.fragment_test1, null)

//        return super.onCreateView(inflater, container, savedInstanceState)

        return view
    }
}