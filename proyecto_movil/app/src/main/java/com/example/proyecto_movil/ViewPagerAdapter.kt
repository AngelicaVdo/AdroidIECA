package com.example.proyecto_movil

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import fragmento_activity

class ViewPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getCount() :Int{
        return 3 //Numero total de paginas
    }

    override fun getItem(position: Int): Fragment {
        return fragmento_activity.newInstance(position + 1)
        //return PageFragment.newInstance( position + 1)
    }
}
