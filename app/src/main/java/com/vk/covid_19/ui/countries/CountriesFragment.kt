package com.vk.covid_19.ui.countries

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.vk.covid_19.R
import com.vk.covid_19.datasource.Covid19Api
import kotlinx.android.synthetic.main.fragment_countries.*
import kotlinx.coroutines.launch


class CountriesFragment : Fragment() {


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_countries, container, false)

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch {
            val countries = Covid19Api.retrofitService.fetchAllCountries()
            val adapter = CountriesAdapter(countries)
            recyclerViewCountries.adapter = adapter
            recyclerViewCountries.layoutManager = LinearLayoutManager(requireContext())
        }
    }
}