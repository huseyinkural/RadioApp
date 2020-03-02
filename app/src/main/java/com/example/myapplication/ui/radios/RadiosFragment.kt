package com.example.myapplication.ui.radios

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.myapplication.R
import com.example.myapplication.data.RadioDataSource
import com.example.myapplication.data.Status
import com.example.myapplication.data.Status.*
import com.example.myapplication.data.remote.RadioServiceProvider
import com.example.myapplication.databinding.FragmentRadiosBinding
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.*


class RadiosFragment : Fragment(){

    private lateinit var binding: FragmentRadiosBinding

    val radioDataSource = RadioDataSource()

    private val popularRadioAdapter = RadiosAdapter()
    private val locationRadioAdapter = RadiosAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_radios,container,false)

        binding.recyclerViewPopularRadios.adapter = popularRadioAdapter
        binding.recyclerViewLocationRadios.adapter = locationRadioAdapter
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        fetchRadioPage()

    }

    @SuppressLint("CheckResult")
    private fun fetchRadioPage(){

        val popularObservable = radioDataSource.fetchPopularRadios()

        val locationObservable = radioDataSource.fetchLocalRadios()

        Observable.combineLatest(popularObservable,locationObservable,RadioPageCombiner())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe{
                renderUI(it)
            }

    }

    private fun renderUI(radiosFragmentViewState: RadioFragmentViewState){

        when(radiosFragmentViewState.popularRadios.status){
            SUCCESS -> {
                popularRadioAdapter.setRadioList(radiosFragmentViewState.popularRadios.data!!)
            }
            LOADING -> TODO()
        }

        when(radiosFragmentViewState.locationRadios.status){
            SUCCESS -> {
                locationRadioAdapter.setRadioList(radiosFragmentViewState.locationRadios.data!!)
            }
            LOADING -> TODO()
        }

    }

    companion object{
        fun newInstance() = RadiosFragment()
    }
}
