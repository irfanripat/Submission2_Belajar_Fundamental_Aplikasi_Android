package com.irfan.githubuser.fragment.following

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.irfan.githubuser.activity.detail.DetailActivity
import com.irfan.githubuser.activity.main.adapter.GithubAdapter
import com.irfan.githubuser.databinding.FragmentFollowingBinding
import com.irfan.githubuser.util.Commons.hide
import com.irfan.githubuser.util.Commons.show

class FollowingFragment : Fragment() {

    private lateinit var binding: FragmentFollowingBinding
    private lateinit var followingViewModel: FollowingViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentFollowingBinding.inflate(inflater, container, false)
        followingViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(FollowingViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val username = requireActivity().intent.getStringExtra(DetailActivity.EXTRA_GITHUB_USER)

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())

        getData(username!!)

        followingViewModel.isSuccess.observe(viewLifecycleOwner, {isSuccess->
            when(isSuccess) {
                0 -> {
                    binding.layoutError.root.show()
                    binding.layoutNoData.root.hide()
                    binding.shimmerLayout.hide()
                }
            }
        })

        binding.layoutError.btnRefresh.setOnClickListener {
            getData(username)
        }
    }

    private fun getData(username: String) {
        showShimmer()
        followingViewModel.getListFollowing(username).observe(viewLifecycleOwner, {
            val githubAdapter = GithubAdapter(it){}

            hideShimmer()

            if (it.isNullOrEmpty()) {
                binding.layoutNoData.root.show()
            } else {
                binding.layoutNoData.root.hide()
            }

            binding.recyclerView.adapter = githubAdapter
        })
    }

    private fun showShimmer() {
        binding.layoutError.root.hide()
        binding.layoutNoData.root.hide()
        binding.shimmerLayout.apply {
            startShimmer()
            show()
        }
    }

    private fun hideShimmer() {
        binding.shimmerLayout.apply {
            hide()
            stopShimmer()
        }
    }

}