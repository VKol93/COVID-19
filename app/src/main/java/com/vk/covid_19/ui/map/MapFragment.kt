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
        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */

        lifecycleScope.launch {
            val countriesList: List<CountryData> = Covid19Api.retrofitService.fetchAllCountries()
            val minCases = countriesList.minByOrNull { it.casesPerOneMillion }?.casesPerOneMillion ?: 0.0
            /*val minCasesCountry: CountryData? = countriesList.minByOrNull { it.cases }
            val minCases = minCasesCountry?.cases ?: 0*/
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

//0 -> min..25%max, 1->25%max..50%max
/*        var group: Int
        if (country.cases >= minCases && country.cases <= maxCases * 0.25)
            group = 0
        if (country.cases > maxCases * 0.25 && country.cases <= maxCases * 0.5)
            group = 1
        if (country.cases > maxCases * 0.5 && country.cases <= maxCases * 0.75)
            group = 2
        else
            group = 3*/

}