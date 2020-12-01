package com.vk.covid_19.ui.world

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.vk.covid_19.R
import com.vk.covid_19.datasource.Covid19Api
import kotlinx.android.synthetic.main.fragment_world.*
import kotlinx.coroutines.launch

class WorldFragment : Fragment() {
    val viewModel: WorldDataViewModel by viewModels()
//    val viewModel = WorldDataViewModel()
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_world, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.worldLiveData.observe(viewLifecycleOwner){globalData->
            worldCasesNumbertextView.text = globalData.cases.toString()
            worldDeathsNumberTextView.text = globalData.deaths.toString()
            worldRecoveredNumberTextView.text = globalData.recovered.toString()
            worldCasesTextView.visibility = View.VISIBLE
            worldDeathsTextView.visibility = View.VISIBLE
            worldRecoveredTextView.visibility = View.VISIBLE
        }

        viewModel.refreshData()

        updateStatsButton.setOnClickListener{
           viewModel.refreshData()
        }

    }
}