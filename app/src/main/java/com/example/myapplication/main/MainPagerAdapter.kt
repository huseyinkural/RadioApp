package com.example.myapplication.main

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.myapplication.R
import com.example.myapplication.favorites.FavoritesFragment
import com.example.myapplication.radios.RadiosFragment
import java.lang.IllegalStateException

class MainPagerAdapter(context: Context, fragmentManager: FragmentManager, behavior:Int) : FragmentStatePagerAdapter(fragmentManager,behavior){

    private val tabs = context.applicationContext.resources.getStringArray(R.array.tabs);
    override fun getItem(position: Int): Fragment {
        return when(position){
            TAB_INDEX_RADIOS -> RadiosFragment.newInstance()
            TAB_INDEX_FAVORITES -> FavoritesFragment.newInstance()
            else -> throw IllegalStateException("Cannot find any fragment. position:  $position");

        }
    }



    override fun getCount(): Int =  2

    override fun getPageTitle(position: Int): CharSequence? {
        return tabs[position].toUpperCase()
    }

    companion object{
        private const val TAB_INDEX_RADIOS= 0
        private const val TAB_INDEX_FAVORITES= 1

    }

}