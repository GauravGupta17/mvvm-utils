package com.gaurav.dask.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.gaurav.dask.core.ui.BaseActivity
import com.gaurav.dask.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initViews()
    }
    private fun initViews()
    {

    }

}