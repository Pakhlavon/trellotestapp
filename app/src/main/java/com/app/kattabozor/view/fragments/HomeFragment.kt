package com.app.kattabozor.view.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.kattabozor.R
import com.app.kattabozor.databinding.FragmentHomeBinding
import com.app.kattabozor.model.Offers
import com.app.kattabozor.utils.DataHandler
import com.app.kattabozor.utils.LogData
import com.app.kattabozor.view.adapter.ProductAdapter
import com.app.kattabozor.viewModel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment :Fragment(R.layout.fragment_home) {
    lateinit var binding: FragmentHomeBinding
    var newList: List<Offers> = emptyList()

    @Inject
    lateinit var newsAdapter: ProductAdapter

    val viewModel: MainViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentHomeBinding.bind(view)
        viewModel.getAllData()
        init()

        viewModel.getProductsLines.observe(viewLifecycleOwner, { dataHandler ->
            when (dataHandler) {
                is DataHandler.Success -> {
                    binding.progressBar.visibility = View.GONE
                    newsAdapter.differ.submitList(dataHandler.data!!.offers)
                }

                is DataHandler.Error -> {
                    binding.progressBar.visibility = View.GONE
                    LogData("onViewCreated: ERROR " + dataHandler.message)
                }

                is DataHandler.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                    LogData("onViewCreated: LOADING..")
                }
            }
        })
        viewModel.getProductsLines
    }

    private fun init() {
        binding.recyclerView.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }

}