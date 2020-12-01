package com.vk.covid_19.ui.countries

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.vk.covid_19.R
import com.vk.covid_19.datasource.Covid19Api
import com.vk.covid_19.model.CountryData
import kotlinx.android.synthetic.main.fragment_countries.*
import kotlinx.coroutines.launch


class CountriesFragment : Fragment(){//, OnCountryItemClickListener{ v3

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
            val listener = object:OnCountryItemClickListener{ //v1
                override fun onItemClick(country: CountryData, position: Int) {
                    val action = CountriesFragmentDirections.actionNavigationCountriesToCountryDetailsFragment(country.name)
                    findNavController().navigate(action)
                    //Toast.makeText(context, country.cases.toString(), Toast.LENGTH_SHORT ).show()
                }
            }
            //val listener = OnItemClickListenerImpl(requireContext()) v2
            //val adapter = CountriesAdapter(countries, this@CountriesFragment) v3
            val adapter = CountriesAdapter(countries, listener)
            recyclerViewCountries.adapter = adapter
            recyclerViewCountries.layoutManager = LinearLayoutManager(requireContext())
        }
    }


/*    override fun onItemClick(country: CountryData, position: Int) {
        Toast.makeText(context, country.cases.toString(), Toast.LENGTH_SHORT ).show()
    }*/
}
/* v2

class OnItemClickListenerImpl(val context: Context): OnCountryItemClickListener{
    override fun onItemClick(country: CountryData, position: Int) {
        Toast.makeText(context, country.cases.toString(), Toast.LENGTH_SHORT ).show()
    }
} */
