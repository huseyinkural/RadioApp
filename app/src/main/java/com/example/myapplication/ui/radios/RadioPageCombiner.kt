package com.example.myapplication.ui.radios

import com.example.myapplication.data.Resource
import com.example.myapplication.data.remote.Radio
import io.reactivex.functions.BiFunction

class RadioPageCombiner : BiFunction<Resource<List<Radio>>,Resource<List<Radio>>,RadioFragmentViewState>{
    override fun apply(
        popularRadios: Resource<List<Radio>>,
        locationRadios: Resource<List<Radio>>
    ): RadioFragmentViewState {
        return RadioFragmentViewState(popularRadios, locationRadios)
    }

}