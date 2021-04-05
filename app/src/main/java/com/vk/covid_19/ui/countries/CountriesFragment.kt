package com.vk.covid_19.ui.countries

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.vk.covid_19.R
import com.vk.covid_19.model.CountryData
import kotlinx.android.synthetic.main.fragment_countries.*

class CountriesFragment : Fragment() {
    private val viewModel: CountriesViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_countries, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.refreshData()

        val listener = object : OnCountryItemClickListener {
            override fun onItemClick(country: CountryData) {
                val action =
                    CountriesFragmentDirections.actionNavigationCountriesToCountryDetailsFragment(
                        country.name
                    )
                findNavController().navigate(action)
            }
        }

        viewModel.countriesLiveData.observe(viewLifecycleOwner) { countries ->
            if (countries != null) {
                val adapter = CountriesAdapter(countries, listener)
                recyclerViewCountries.adapter = adapter
                recyclerViewCountries.layoutManager = LinearLayoutManager(requireContext())
            }
        }

        viewModel.loadingLiveData.observe(viewLifecycleOwner) { isLoading ->
            progressBar.visibility = if (isLoading)
                View.VISIBLE
            else
                View.INVISIBLE
        }

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                when(position) {
                    0 -> {
                        viewModel.changeSortedType(CountriesViewModel.SortedType.BY_NAME)
                    }
                    1 -> {
                        viewModel.changeSortedType(CountriesViewModel.SortedType.BY_CASES)
                    }
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
    }
}
