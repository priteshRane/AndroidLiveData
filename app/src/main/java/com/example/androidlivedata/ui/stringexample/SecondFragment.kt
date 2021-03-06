package com.example.androidlivedata.ui.stringexample

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.androidlivedata.MainActivity
import com.example.androidlivedata.MainViewModel
import com.example.androidlivedata.R
import com.example.androidlivedata.databinding.FragmentSecondBinding

class SecondFragment : Fragment() {

    private val TAG = "DetailFragment"
    lateinit var viewModel: MainViewModel
    lateinit var binding: FragmentSecondBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_second, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity(), (this.activity as MainActivity).viewModelFactory).get(MainViewModel::class.java)
        viewModel.getDataString().observe(viewLifecycleOwner, Observer {
            Log.i(TAG, it)
            binding.etData.setText(it)
        })

        binding.btnSend.setOnClickListener {
            viewModel.setDataString(binding.etData.text.toString())

            findNavController().popBackStack()
        }
    }
}