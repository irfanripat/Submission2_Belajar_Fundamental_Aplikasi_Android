package com.irfan.githubuser.activity.mainactivity.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.irfan.githubuser.R
import com.irfan.githubuser.model.GithubUser
import de.hdodenhof.circleimageview.CircleImageView

class GithubAdapter(private val listItem: MutableList<GithubUser>, private val listener: (Any) -> Unit) : RecyclerView.Adapter<GithubAdapter.MainViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder =
        MainViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_github_user, parent, false))

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(listItem[position], listener)
    }

    override fun getItemCount() = listItem.size

    inner class MainViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(listItem: GithubUser, listener: (Any) -> Unit) {
            val tvName : TextView = itemView.findViewById(R.id.tv_name)
            val tvUsername : TextView = itemView.findViewById(R.id.tv_username)
            val tvFollowers : TextView = itemView.findViewById(R.id.tv_followers)
            val tvFollowing : TextView = itemView.findViewById(R.id.tv_following)
            val imgAvatar : CircleImageView = itemView.findViewById(R.id.circleImageView)

            tvName.text = listItem.name
            tvUsername.text = String.format("@${listItem.username}")
            tvFollowers.text = listItem.followers
            tvFollowing.text = listItem.following
            Glide.with(view.context)
                .load(listItem.avatar)
                .placeholder(android.R.drawable.progress_indeterminate_horizontal)
                .error(android.R.drawable.stat_notify_error)
                .into(imgAvatar)

            itemView.setOnClickListener{listener(listItem)}
        }
    }
}
