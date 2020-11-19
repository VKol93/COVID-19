package com.vk.covid_19.ui.countries

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.vk.covid_19.R
import com.vk.covid_19.model.CountryData
import kotlinx.android.synthetic.main.country_item.view.*

class CountriesAdapter(val country: List<CountryData>): RecyclerView.Adapter<CountryViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val viewHolder = CountryViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.country_item, parent, false))

        return viewHolder
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        val country = country[position]
        holder.bind(country)
    }

    override fun getItemCount(): Int = country.size
}



class CountryViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
    fun bind(countryData: CountryData){
        itemView.country_name_textView.setText(countryData.name)
        //itemView.country_flag_imageView.setImageDrawable(countryData.info.flag)
        val info = countryData.info
        val flag = info.flag
        Picasso.with(itemView.context)
            .load(flag)
            .into(itemView.country_flag_imageView)
    }
}