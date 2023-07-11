package com.app.trello.view.fragments

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.trello.R
import com.app.trello.databinding.FragmentHomeBinding
import com.app.trello.utils.DataHandler
import com.app.trello.utils.LogData
import com.app.trello.utils.SharedPref
import com.app.trello.view.adapter.BoardItemAdapter
import com.app.trello.viewModel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment :Fragment(R.layout.fragment_home)  {
    lateinit var binding: FragmentHomeBinding

    val viewModel: MainViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentHomeBinding.bind(view)
        viewModel.getAllPosts()

        if (SharedPref.token == ""){
            val action = HomeFragmentDirections
                .actionHomeFragmentToLoginFragment2()
            view.findNavController().navigate(action)
        }
        getBoardsList()

        viewModel.getAllPostsLines.observe(viewLifecycleOwner, { dataHandler ->
            when (dataHandler) {
                is DataHandler.Success -> {
                    binding.progressBar.visibility = View.GONE

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
        viewModel.getAllPostsLines
    }

    fun getBoardsList(){
        val boardsList : ArrayList<String> = ArrayList()
        boardsList.add("New")
        boardsList.add("In progress")
        boardsList.add("In review")
        boardsList.add("Done")
        getBoardsToUI(boardsList)
        println(boardsList)
    }

    fun getBoardsToUI(boardsList : ArrayList<String>){
            binding.boardsListRv.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
            binding.boardsListRv.setHasFixedSize(true)
            val adapter = BoardItemAdapter(boardsList)
            binding.boardsListRv.adapter = adapter

//            adapter.setOnClickListener(object : BoardItemAdapter.OnClickListener{
//                override fun onClick(position: Int, model: String) {
//                    val intent = Intent(context, TaskListActivity::class.java)
//                    intent.putExtra(Constants.DOCUMENT_ID, model.documentID)
//                    startActivity(intent)
//                }
//            })
    }
}