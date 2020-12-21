package com.example.androidlivedata.ui.stringexample.list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.androidlivedata.MainActivity
import com.example.androidlivedata.MainViewModel
import com.example.androidlivedata.R
import com.example.androidlivedata.databinding.FragmentListBinding

class ListFragment : Fragment() {

    private val TAG = "ListFragment"
    lateinit var viewModel: MainViewModel
    lateinit var binding: FragmentListBinding

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_list, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity(), (this.activity as MainActivity).viewModelFactory).get(MainViewModel::class.java)
        viewModel.getData().observe(viewLifecycleOwner, Observer {
            Log.i(TAG, it)
            binding.etData.setText(it)
        })

        binding.btnSend.setOnClickListener {
            viewModel.setDate(binding.etData.text.toString())

            val action = ListFragmentDirections.actionListFragmentToDetailsFragment()
            Navigation.findNavController(it).navigate(action)
        }
    }
}