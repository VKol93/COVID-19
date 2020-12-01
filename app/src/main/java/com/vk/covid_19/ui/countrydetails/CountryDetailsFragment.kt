package com.vk.covid_19.ui.countrydetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.vk.covid_19.R
import com.vk.covid_19.datasource.Covid19Api
import com.vk.covid_19.model.CountryData
import kotlinx.android.synthetic.main.fragment_country_details.*
import kotlinx.coroutines.launch


class CountryDetailsFragment : Fragment() {
    val args: CountryDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_country_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val countryName: String = args.countryName
        lifecycleScope.launch {
            val countries: List<CountryData> = Covid19Api.retrofitService.fetchAllCountries()
            val country: CountryData? = countries.find { it.name == countryName}
            if (country != null) {
                casesTextView.text = country.cases.toString()
                recoveredTextView.text = country.recovered.toString()
            } else {
                casesTextView.text = "Error"
            }
        }

    }
}