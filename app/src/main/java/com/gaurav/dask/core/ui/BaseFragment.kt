package com.gaurav.dask.core.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.gaurav.dask.core.ui.utils.viewBinding

open class BaseFragment(private val layoutRes:Int) :Fragment(layoutRes){
fun navigate(navDirections: NavDirections){
    findNavController().navigate(navDirections)
}

fun backToPrevious(){
    findNavController().navigateUp()
}

}