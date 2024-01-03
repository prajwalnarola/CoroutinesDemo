package com.example.coroutinesdemo

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.coroutinesdemo.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: MainActivityAdapter
    private lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = MainActivityAdapter(emptyList())
        binding.rvFirst.adapter = adapter

        viewModel = ViewModelProvider(this)[MainActivityViewModel::class.java]
        viewModel.dataObserver.observe(this) { newData ->

            binding.shimmerLayout.stopShimmer()
            binding.shimmerLayout.visibility = View.GONE
            binding.refreshLayout.isRefreshing = false
            binding.pbFirst.visibility = View.GONE

            if (newData != null) {
                adapter.setData(newData)
            } else {
            //
            }
        }

        Log.i("TAG", "Handling UI 1 on ${Thread.currentThread().name}")
        binding.refreshLayout.setOnRefreshListener {
            binding.rvFirst.visibility = View.GONE
            binding.pbFirst.visibility = View.GONE
            binding.refreshLayout.isRefreshing = false
            binding.shimmerLayout.visibility = View.VISIBLE
            binding.shimmerLayout.startShimmer()
            CoroutineScope(Dispatchers.IO).launch {
                Log.i("TAG", "Handling networkCall 2 on ${Thread.currentThread().name}")
                demoSusFunc(1500)
                viewModel.getData()
                CoroutineScope(Dispatchers.Main).launch  {
                    Log.i("TAG", "Handling UI under Io on ${Thread.currentThread().name}")
                    binding.rvFirst.visibility = View.VISIBLE
                }
            }
        }
        loadInitialData()

    }

    private fun loadInitialData() {
        binding.rvFirst.visibility = View.GONE
        binding.pbFirst.visibility = View.GONE
        binding.refreshLayout.isRefreshing = false
        binding.shimmerLayout.visibility = View.VISIBLE
        binding.shimmerLayout.startShimmer()

        CoroutineScope(Dispatchers.IO).launch {
            demoSusFunc(1500)
            viewModel.getData()
            CoroutineScope(Dispatchers.Main).launch {
                binding.rvFirst.visibility = View.VISIBLE
            }
        }
    }

    private suspend fun demoSusFunc(time : Long){
        delay(time)
    }

}