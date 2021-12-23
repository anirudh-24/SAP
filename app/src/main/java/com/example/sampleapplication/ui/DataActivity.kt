package com.example.sampleapplication.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sampleapplication.databinding.ActivityDataBinding
import com.example.sampleapplication.model.DetailsData
import com.example.sampleapplication.network.Status
import com.example.sampleapplication.ui.adapter.DataAdapter
import com.example.sampleapplication.viewmodel.DataViewModel

class DataActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDataBinding
    private lateinit var mDataViewModel: DataViewModel
    private lateinit var mDataListAdapter: DataAdapter
    private var mUserAndBirthdayList: List<DetailsData> = emptyList()
    private var mApiType: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mDataViewModel = ViewModelProvider(this).get(DataViewModel::class.java)

        bindObservers()

        mApiType = intent.extras?.getInt("value")!!
        mDataListAdapter = DataAdapter(mUserAndBirthdayList, mApiType)

        //consume api based on data type
        if (mApiType == 1) {
            binding.recyclerData.layoutManager = LinearLayoutManager(this)
            binding.recyclerData.adapter = mDataListAdapter
            mDataViewModel.getBirthdayListData()
        } else {
            binding.recyclerData.layoutManager = GridLayoutManager(this, 2)
            binding.recyclerData.adapter = mDataListAdapter
            mDataViewModel.getDescriptionListData()
        }

    }

    //listen to api data change
    private fun bindObservers() {

        mDataViewModel.volumesResponseLiveData?.observe(this, Observer {
            when (it.status) {
                Status.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                Status.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    mDataListAdapter.setListData(it.data)
                }
                Status.ERROR -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                }

            }
        })
    }
}
