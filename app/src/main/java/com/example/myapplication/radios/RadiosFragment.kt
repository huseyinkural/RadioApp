package com.example.myapplication.radios

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.myapplication.R
import com.example.myapplication.Radio
import com.example.myapplication.RadioServiceProvider
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class RadiosFragment : Fragment(){

    private val radioServiceProvider = RadioServiceProvider()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_radios,container,false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        radioServiceProvider
            .getRadioService()
            .getPopularRadios()
            .enqueue(object : Callback<List<Radio>> {
                override fun onResponse(call: Call<List<Radio>>, response: Response<List<Radio>>) {
                    Log.v("Test", "Success popular: ${response.body().toString()}")
                }
                override fun onFailure(call: Call<List<Radio>>, t: Throwable) {
                    Log.v("Test", "Failed")
                }
            })

        radioServiceProvider
            .getRadioService()
            .getLocationRadios()
            .enqueue(object : Callback<List<Radio>> {
                override fun onResponse(call: Call<List<Radio>>, response: Response<List<Radio>>) {
                    Log.v("Test", "Success location: ${response.body().toString()}")
                }
                override fun onFailure(call: Call<List<Radio>>, t: Throwable) {
                    Log.v("Test", "Failed")
                }
            })

    }
    companion object{
        fun newInstance() = RadiosFragment()
    }
}
