package com.irfan.githubuser.activity.detail.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.irfan.githubuser.fragment.followers.FollowersFragment
import com.irfan.githubuser.fragment.following.FollowingFragment

internal class FragmentAdapter(fm: FragmentManager, private var totalTabs: Int) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> FollowersFragment()
            1 -> FollowingFragment()
            else -> getItem(position)
        }
    }

    override fun getCount(): Int = totalTabs
}