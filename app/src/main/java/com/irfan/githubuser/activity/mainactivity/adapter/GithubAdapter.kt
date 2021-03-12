package com.irfan.githubuser.activity.mainactivity.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.irfan.githubuser.R
import com.irfan.githubuser.databinding.ItemGithubUserBinding
import com.irfan.githubuser.model.GithubUser

class GithubAdapter(private val listItem: MutableList<GithubUser>, private val listener: (Any) -> Unit) : RecyclerView.Adapter<GithubAdapter.MainViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder =
        MainViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_github_user, parent, false))

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(listItem[position], listener)
    }

    override fun getItemCount() = listItem.size

    inner class MainViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ItemGithubUserBinding.bind(view)
        fun bind(listItem: GithubUser, listener: (Any) -> Unit) {
            binding.tvName.text = listItem.name
            binding.tvUsername.text = String.format("@${listItem.username}")
            binding.tvFollowers.text = listItem.followers
            binding.tvFollowing.text = listItem.following
            Glide.with(view.context)
                .load(listItem.avatar)
                .placeholder(android.R.drawable.progress_indeterminate_horizontal)
                .error(android.R.drawable.stat_notify_error)
                .into(binding.circleImageView)

            itemView.setOnClickListener{listener(listItem)}
        }
    }
}
