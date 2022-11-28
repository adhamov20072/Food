package com.aimardon.food.fragments

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import com.aimardon.food.adapters.Adapter
import com.aimardon.food.databinding.FragmentHomeBinding
import com.aimardon.food.network.NetworkApi
import com.aimardon.food.network.NetworkResult
import com.aimardon.food.network.RetrofitHelper
import com.aimardon.food.viewmodels.HomeViewModel
import com.aimardon.food.viewmodels.HomeViewModelFactory

class HomeFragment : Fragment() {
    lateinit var binding: FragmentHomeBinding
    lateinit var adapter: Adapter
    lateinit var homeViewModel: HomeViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        adapter = Adapter()
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.recyclerView.adapter=adapter
        val retrofit=RetrofitHelper.getRetrofit().create(NetworkApi::class.java)
        val factory=HomeViewModelFactory(retrofit,requireActivity().application)
        homeViewModel=ViewModelProvider(requireActivity(),factory)[HomeViewModel::class.java]
        homeViewModel.get(50,true,"04ebf79f1e5a453b905f660a6f0b0eaa")
        homeViewModel.food.observe(viewLifecycleOwner){
            when(it){
                is NetworkResult.Error -> {
                    Toast.makeText(requireContext(), "error", Toast.LENGTH_SHORT).show()
                }
                is NetworkResult.Loading -> {

                }
                is NetworkResult.Success -> {
                    adapter.submitList(it.data)
                }
            }
        }
    }
}
