package com.example.androidlivedata.ui.recyclerviewdummyexample.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.androidlivedata.MainActivity
import com.example.androidlivedata.MainViewModel
import com.example.androidlivedata.R
import com.example.androidlivedata.data.network.responses.Movie
import com.example.androidlivedata.databinding.FragmentRvDummyDataDetailsBinding

class RVDummyDataDetailsFragment : Fragment() {

    private val TAG = "RVDetailsFragment"
    lateinit var viewModel: MainViewModel
    lateinit var binding: FragmentRvDummyDataDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity(), (this.activity as MainActivity).viewModelFactory).get(MainViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_rv_dummy_data_details, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val args = requireArguments().getParcelable<Movie>("movie")
        if (args != null) {
            binding.tvDetails.text = "${args.name.toString()}\n${args.rating.toString()}\n${args.duration.toString()}\n${args.directors.toString()}"
        }

        binding.btnDelete.setOnClickListener {
            if (args != null) {
                viewModel.deleteElementFromDataMutableList(args)
                findNavController().popBackStack()
            }
        }

        binding.btnChangeRating.setOnClickListener {
            if (args != null) {
                viewModel.changeRatingFromDataMutableList(args)
                findNavController().popBackStack()
            }
        }
    }
}