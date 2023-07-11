package com.app.trello.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.trello.R
import com.app.trello.databinding.FragmentHomeBinding
import com.app.trello.databinding.FragmentLoginBinding
import com.app.trello.utils.DataHandler
import com.app.trello.utils.LogData
import com.app.trello.utils.SharedPref
import com.app.trello.viewModel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment :Fragment(R.layout.fragment_login) {
    lateinit var binding: FragmentLoginBinding

    val viewModel: MainViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentLoginBinding.bind(view)

        binding.signInBtn.setOnClickListener {
            println(binding.signInEmailEt.text.toString())
            println( binding.signInPasswordEt.text.toString())
            viewModel.loginData(
                binding.signInEmailEt.text.toString(),
                binding.signInPasswordEt.text.toString()
            )
        }

        viewModel.getUsersLines.observe(viewLifecycleOwner, { dataHandler ->
            when (dataHandler) {
                is DataHandler.Success -> {
                    SharedPref.token = dataHandler.data!!.token
                    SharedPref.user_name  = dataHandler.data.user.full_name
                    viewModel.getAllPosts()

                    val action = LoginFragmentDirections
                        .actionLoginFragmentToHomeFragment2()
                    view.findNavController().navigate(action)
                }

                is DataHandler.Error -> {
                    LogData("onViewCreated: ERROR " + dataHandler.message)
                }

                is DataHandler.Loading -> {
                    LogData("onViewCreated: LOADING..")
                }
            }
        })
        viewModel.getUsersLines
    }
}