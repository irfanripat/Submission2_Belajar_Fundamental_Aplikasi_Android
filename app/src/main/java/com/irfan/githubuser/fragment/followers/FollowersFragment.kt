package com.irfan.githubuser.fragment.followers

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.irfan.githubuser.activity.detail.DetailActivity
import com.irfan.githubuser.activity.main.adapter.GithubAdapter
import com.irfan.githubuser.databinding.FragmentFollowersBinding
import com.irfan.githubuser.util.Commons.hide
import com.irfan.githubuser.util.Commons.show

class FollowersFragment : Fragment() {

    private lateinit var binding: FragmentFollowersBinding
    private lateinit var followersViewModel: FollowersViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentFollowersBinding.inflate(inflater, container, false)
        followersViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(FollowersViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val username = requireActivity().intent.getStringExtra(DetailActivity.EXTRA_GITHUB_USER)

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())

        getData(username!!)

        followersViewModel.isSuccess.observe(viewLifecycleOwner, {isSuccess ->
            when(isSuccess) {
                0 -> {
                    binding.layoutError.root.show()
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
        followersViewModel.getListFollower(username).observe(viewLifecycleOwner,  {
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
