package com.example.androidlivedata.ui.recyclerviewdummyexample.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidlivedata.MainActivity
import com.example.androidlivedata.MainViewModel
import com.example.androidlivedata.R
import com.example.androidlivedata.data.network.responses.Movie
import com.example.androidlivedata.databinding.FragmentRvDummyDataListBinding
import com.example.androidlivedata.ui.RecyclerViewListAdapter
import com.example.androidlivedata.ui.RecyclerViewListClickInterface
import com.example.androidlivedata.ui.RecyclerViewListInterface

class RVApiDataListFragment : Fragment(), RecyclerViewListInterface,
    RecyclerViewListClickInterface {

    private val TAG = "RVListFragment"
    lateinit var viewModel: MainViewModel
    lateinit var binding: FragmentRvDummyDataListBinding
    lateinit var recylerViewListAdapter: RecyclerViewListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(
            requireActivity(),
            (this.activity as MainActivity).viewModelFactory
        ).get(MainViewModel::class.java)
        viewModel.recyclerViewListInterface = this
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_rv_dummy_data_list,
            container,
            false
        )
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        recylerViewListAdapter = RecyclerViewListAdapter(this)

        binding.recyclerView.also {
            it.layoutManager = LinearLayoutManager(requireContext())
            it.setHasFixedSize(true)
            it.adapter = recylerViewListAdapter
        }

        viewModel.getMovieList()
        viewModel.movies.observe(viewLifecycleOwner, Observer { movies ->
            recylerViewListAdapter.notifyDataSetChanged()
            recylerViewListAdapter.addMovies(movies)
        })
    }

    override fun showProgressBar() {
        binding.progressBar.visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        binding.progressBar.visibility = View.GONE
    }

    override fun onMovieItemClick(view: View, movie: Movie) {
        val action =
            RVApiDataListFragmentDirections.actionRVApiDataListFragmentToRVApiDataDetailsFragment(
                movie
            )
        Navigation.findNavController(view).navigate(action)
    }
}