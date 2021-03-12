package com.irfan.githubuser.activity.mainactivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.irfan.githubuser.activity.DetailActivity
import com.irfan.githubuser.activity.mainactivity.adapter.GithubAdapter
import com.irfan.githubuser.databinding.ActivityMainBinding
import com.irfan.githubuser.model.GithubData
import com.irfan.githubuser.model.GithubUser

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var listGithubUser = arrayListOf<GithubUser>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.setHasFixedSize(true)
        listGithubUser.addAll(GithubData(this).listData)

        showRecyclerData()
    }

    private fun showRecyclerData() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        val githubAdapter = GithubAdapter(listGithubUser) {
            val githubUser = it as GithubUser
            showDetailUser(githubUser)
        }

        binding.recyclerView.adapter = githubAdapter
    }

    private fun showDetailUser(githubUser: GithubUser) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(DetailActivity.EXTRA_GITHUB_USER, githubUser)
        startActivity(intent)
    }
}