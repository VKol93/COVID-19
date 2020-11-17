package com.vk.covid_19.ui.world

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.vk.covid_19.R
import com.vk.covid_19.datasource.Covid19Api
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {
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
        button.setOnclickListener{
            lifecycleScope.launch {
                val globalData = Covid19Api.retrofitService.getGlobalData()
                casesTextView.text = globalData.cases
            }
        }


    }
}