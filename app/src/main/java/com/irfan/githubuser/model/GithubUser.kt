package com.irfan.githubuser.model

import android.content.Context
import android.os.Parcelable
import com.irfan.githubuser.R
import kotlinx.parcelize.Parcelize

@Parcelize
data class GithubUser(
    val username: String?,
    val name: String?,
    val location: String?,
    val repository: String?,
    val company: String?,
    val followers: String?,
    val following: String?,
    val avatar: Int?
) :Parcelable

class GithubData(context: Context) {
    val listUsername =  context.resources.getStringArray(R.array.username)
    val listName = context.resources.getStringArray(R.array.name)
    val listLocation = context.resources.getStringArray(R.array.location)
    val listRepository = context.resources.getStringArray(R.array.repository)
    val listCompany = context.resources.getStringArray(R.array.company)
    val listFollowers = context.resources.getStringArray(R.array.followers)
    val listFollowing = context.resources.getStringArray(R.array.following)
    val listAvatar = context.resources.obtainTypedArray(R.array.avatar)

    val listData: ArrayList<GithubUser>
        get() {
            val list = arrayListOf<GithubUser>()
            for(position in listUsername.indices) {
                val githubUser = GithubUser(
                    listUsername[position],
                    listName[position],
                    listLocation[position],
                    listRepository[position],
                    listCompany[position],
                    listFollowers[position],
                    listFollowing[position],
                    listAvatar.getResourceId(position, -1)
                )
                list.add(githubUser)
            }
            return list
        }
}
