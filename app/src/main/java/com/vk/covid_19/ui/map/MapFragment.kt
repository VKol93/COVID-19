package com.vk.covid_19.ui.map

import androidx.fragment.app.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.vk.covid_19.R
import com.vk.covid_19.datasource.Covid19Api
import com.vk.covid_19.model.CountryData
import kotlinx.coroutines.launch

class MapFragment : Fragment() {

    private val callback = OnMapReadyCallback { googleMap ->

        lifecycleScope.launch {
            val countriesList: List<CountryData> = Covid19Api.retrofitService.fetchAllCountries()
            val minCases = countriesList.minByOrNull { it.casesPerOneMillion }?.casesPerOneMillion ?: 0.0
            val maxCases = countriesList.maxByOrNull { it.casesPerOneMillion }?.casesPerOneMillion ?: 0.0
            for (country in countriesList) {
                val countryCoords = LatLng(country.info.lat, country.info.long)
                val group = getGroupNumberByCases(minCases, maxCases, country)
                val markerIcon = when (group) {
                    0 -> BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)
                    1 -> BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW)
                    2 -> BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)
                    else -> BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)
                }
                googleMap.addMarker(
                    MarkerOptions().position(countryCoords).icon(markerIcon)
                        .title(country.name)
                )
            }
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_map, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }

    private fun getGroupNumberByCases(minCases: Double, maxCases: Double, country: CountryData): Int =
        when {
            country.casesPerOneMillion >= minCases && country.casesPerOneMillion <= maxCases * 0.25 -> 0
            country.casesPerOneMillion > maxCases * 0.25 && country.casesPerOneMillion <= maxCases * 0.5 -> 1
            country.casesPerOneMillion > maxCases * 0.5 && country.casesPerOneMillion <= maxCases * 0.75 -> 2
            else -> 3
        }
}