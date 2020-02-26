package com.example.myapplication.ui.radios

import com.example.myapplication.data.Resource
import com.example.myapplication.data.remote.Radio

data class RadioFragmentViewState(val popularRadios: Resource<List<Radio>>,
                                  val locationRadios: Resource<List<Radio>>)