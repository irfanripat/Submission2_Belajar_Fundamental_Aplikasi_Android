package com.irfan.githubuser.fragment.followers

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.irfan.githubuser.api.ApiInterface
import com.irfan.githubuser.api.RetrofitClient
import com.irfan.githubuser.model.DetailUser
import com.irfan.githubuser.util.Constant
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class FollowersViewModel: ViewModel() {

    private val viewModelJob = SupervisorJob()
    private val _listUser = MutableLiveData<ArrayList<DetailUser>>()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val listUser : LiveData<ArrayList<DetailUser>>
        get() = _listUser

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    fun getListFollower(username: String): LiveData<ArrayList<DetailUser>> {
        val api = RetrofitClient.getRetrofitInstance().create(ApiInterface::class.java)
        val listItem = ArrayList<DetailUser>()
        uiScope.launch {
            try {
                val response = api.getListFollower("token${Constant.TOKEN}", username)
                if (response.isSuccessful) {
                    for (user in response.body()!!) {
                        listItem.add(user)
                    }
                    Log.d("response", listItem.toString())
                    _listUser.postValue(listItem)
                }
            } catch (e: Throwable) {
                Log.e("Throwable", e.toString())
            }
        }
        return listUser
    }
}