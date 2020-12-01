package com.vk.covid_19.ui.world

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.vk.covid_19.R
import com.vk.covid_19.databinding.FragmentWorldBinding
import kotlinx.android.synthetic.main.fragment_world.*

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

        val binding = FragmentWorldBinding.bind(view)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        /*
        viewModel.worldLiveData.observe(viewLifecycleOwner){globalData->
            worldCasesNumbertextView.text = globalData.cases.toString()
            worldDeathsNumberTextView.text = globalData.deaths.toString()
            worldRecoveredNumberTextView.text = globalData.recovered.toString()
            worldCasesTextView.visibility = View.VISIBLE
            worldDeathsTextView.visibility = View.VISIBLE
            worldRecoveredTextView.visibility = View.VISIBLE
        }*/

/*        viewModel.refreshLiveData.observe(viewLifecycleOwner){ isRefreshingNow ->
            progressBar.visibility = if (isRefreshingNow)
                View.VISIBLE
            else
                View.GONE
        }*/

        viewModel.refreshData()

        updateStatsButton.setOnClickListener{
           viewModel.refreshData()
        }

    }
}