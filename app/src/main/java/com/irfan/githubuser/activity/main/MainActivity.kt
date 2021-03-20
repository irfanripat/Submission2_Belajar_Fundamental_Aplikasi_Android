package com.irfan.githubuser.activity.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.irfan.githubuser.activity.main.adapter.GithubAdapter
import com.irfan.githubuser.databinding.ActivityMainBinding
import com.irfan.githubuser.util.Commons.hide
import com.irfan.githubuser.util.Commons.show

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(MainViewModel::class.java)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        binding.inputUsername.setOnEditorActionListener { _, p1, _ ->
            if (p1 == EditorInfo.IME_ACTION_DONE) {
                showShimmer()
                mainViewModel.setSearchQuery(binding.inputUsername.text.toString())
            }
            true
        }

        mainViewModel.getSearchResult().observe(this, {
            val githubAdapter = GithubAdapter(it) {

            }

            hideShimmer()

            if (it.isNullOrEmpty()) {
                binding.layoutNoData.root.show()
            } else {
                binding.layoutNoData.root.hide()
                githubAdapter.notifyDataSetChanged()
            }

            binding.recyclerView.adapter = githubAdapter
        })
    }

    private fun showShimmer() {
        binding.apply {
            layoutNoData.root.hide()
            recyclerView.hide()
            shimmerLayout.apply {
                show()
                startShimmer()
            }
        }
    }

    private fun hideShimmer() {
        binding.apply {
            shimmerLayout.apply {
                stopShimmer()
                hide()
            }
            recyclerView.show()
        }
    }


}