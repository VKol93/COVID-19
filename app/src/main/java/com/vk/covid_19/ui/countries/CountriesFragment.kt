package com.vk.covid_19.ui.countries

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.vk.covid_19.R
import com.vk.covid_19.model.CountryData
import kotlinx.android.synthetic.main.fragment_countries.*


class CountriesFragment : Fragment() {
    //, OnCountryItemClickListener{ v3
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

        val listener = object : OnCountryItemClickListener { //v1
            override fun onItemClick(country: CountryData, position: Int) {
                val action =
                    CountriesFragmentDirections.actionNavigationCountriesToCountryDetailsFragment(
                        country.name
                    )
                findNavController().navigate(action)
            }
        }
        //val listener = OnItemClickListenerImpl(requireContext()) v2
        //val adapter = CountriesAdapter(countries, this@CountriesFragment) v3
        viewModel.countriesLiveData.observe(viewLifecycleOwner) { countries ->
            if (countries != null) {
                val adapter = CountriesAdapter(countries, listener)
                recyclerViewCountries.adapter = adapter
                recyclerViewCountries.layoutManager = LinearLayoutManager(requireContext())
            }
        }
        viewModel.refreshData()
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
/*        val searchViewListener = object:SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                return true
            }

        }
        searchView.setOnQueryTextListener(searchViewListener)*/
    }
}



/*    override fun onItemClick(country: CountryData, position: Int) {
        Toast.makeText(context, country.cases.toString(), Toast.LENGTH_SHORT ).show()
    }*/
/* v2

class OnItemClickListenerImpl(val context: Context): OnCountryItemClickListener{
    override fun onItemClick(country: CountryData, position: Int) {
        Toast.makeText(context, country.cases.toString(), Toast.LENGTH_SHORT ).show()
    }
} */
