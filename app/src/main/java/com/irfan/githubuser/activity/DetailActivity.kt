package com.irfan.githubuser.activity

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.irfan.githubuser.R
import com.irfan.githubuser.databinding.ActivityDetailBinding
import com.irfan.githubuser.model.GithubUser
import com.irfan.githubuser.util.Constant

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    companion object {
        const val EXTRA_GITHUB_USER = "github"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        val githubUser = intent.getParcelableExtra<GithubUser>(EXTRA_GITHUB_USER)

        githubUser?.apply {
            binding.tvName.text = name
            binding.tvUsername.text = String.format("@$username")
            binding.tvLocation.text = location
            binding.tvCompany.text = company
            binding.tvFollowers.text = followers
            binding.tvFollowing.text = following
            binding.tvRepository.text = repository
            Glide.with(this@DetailActivity)
                .load(avatar)
                .into(binding.imgAvatar)
        }

        binding.btnVisit.setOnClickListener {
            val url = Constant.BASE_URL + githubUser?.username
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(url)
            startActivity(intent)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}