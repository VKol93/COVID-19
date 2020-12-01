package com.vk.covid_19.ui.bindingadapter

import android.view.View
import android.widget.ProgressBar
import androidx.databinding.BindingAdapter

@BindingAdapter(value = ["app:visibleIfTrue"], requireAll = false)
fun visibleIfTrue(view: View, value: Boolean){
    view.visibility = if(value)
        View.VISIBLE
    else
        View.GONE
}