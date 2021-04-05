package com.vk.covid_19.ui.countries
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.vk.covid_19.R
import com.vk.covid_19.model.CountryData
import kotlinx.android.synthetic.main.country_item.view.*
class CountriesAdapter(val countries: List<CountryData>, val clickListener: OnCountryItemClickListener): RecyclerView.Adapter<CountryViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val viewHolder = CountryViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.country_item, parent, false))
        return viewHolder
    }
    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        val countryData = countries[position]
        holder.bind(countryData)
        holder.itemView.setOnClickListener{
            clickListener.onItemClick(countryData)
        }
    }
    override fun getItemCount(): Int = countries.size
}
class CountryViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    fun bind(countryData: CountryData) {
        itemView.country_name_textView.text = countryData.name
        itemView.countryCasesTextView.text = countryData.cases.toString()
        Picasso.with(itemView.context)
            .load(countryData.info.flag)
            .into(itemView.country_flag_imageView)

    }
}
interface OnCountryItemClickListener{
    fun onItemClick(country: CountryData)
}
