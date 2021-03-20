package com.irfan.githubuser.activity.detail

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

class DetailViewModel : ViewModel() {
    private val viewModelJob = SupervisorJob()
    private val _user = MutableLiveData<DetailUser>()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val user : LiveData<DetailUser>
        get() = _user

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }


    fun getDetailUser(username: String): LiveData<DetailUser> {
        val api = RetrofitClient.getRetrofitInstance().create(ApiInterface::class.java)
        uiScope.launch {
            try {
                val response = api.getDetailUser("token ${Constant.TOKEN}", username)
                if (response.isSuccessful) {
                    val data = response.body()
                    Log.d("user", data.toString())
                    _user.postValue(data)
                }
            } catch (e: Throwable) {
                Log.e("Throwable", e.toString())
            }
        }
        return user
    }
}