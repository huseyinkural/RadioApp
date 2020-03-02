package com.example.myapplication.ui.radios

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.data.remote.Radio
import com.example.myapplication.databinding.ItemRadioBinding
import com.squareup.picasso.Picasso

class RadiosAdapter: RecyclerView.Adapter<RadiosAdapter.RadioItemViewHolder>() {

    private val radiosList = arrayListOf<Radio>()

    fun setRadioList(radioList: List<Radio>){
        this.radiosList.clear()
        this.radiosList.addAll(radioList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RadioItemViewHolder = RadioItemViewHolder.create(parent)


    override fun getItemCount(): Int = radiosList.size

    override fun onBindViewHolder(holder: RadioItemViewHolder, position: Int) = holder.bind(radiosList[position])

    class RadioItemViewHolder(private val binding: ItemRadioBinding): RecyclerView.ViewHolder(binding.root){

        fun bind(radio: Radio){
            binding.textViewRadioTitle.text = radio.radioName
            binding.textViewRadioNumber.text = radio.band
            Picasso.get().load(radio.logo_big).into(binding.imageViewAvatar)

        }

        companion object{
            fun create(parent: ViewGroup):RadioItemViewHolder{

                val binding = DataBindingUtil.inflate<ItemRadioBinding>(LayoutInflater.from(parent.context),
                    R.layout.item_radio,parent,false)

                return RadioItemViewHolder(binding)

            }
        }

    }
}