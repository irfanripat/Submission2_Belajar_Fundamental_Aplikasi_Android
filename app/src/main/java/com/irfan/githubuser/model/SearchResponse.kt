package com.irfan.githubuser.model

data class SearchResponse(
    val total_count: Int?,
    val incomplete_results: Boolean?,
    val items: ArrayList<DetailUser>?
)