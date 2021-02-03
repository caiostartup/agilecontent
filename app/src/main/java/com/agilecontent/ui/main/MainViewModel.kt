package com.agilecontent.ui.main

import androidx.lifecycle.ViewModel
import com.agilecontent.models.RepositoryModel
import com.agilecontent.services.GitHubService
import com.agilecontent.services.RestCallback
import com.agilecontent.services.ServiceManager.retrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {

    fun getReposByUsername(username: String, callback: RestCallback<List<RepositoryModel>?, Exception>) {
        val service = retrofit.create(GitHubService::class.java)
        service.listRepos(username).enqueue(object: Callback<List<RepositoryModel>?> {
            override fun onFailure(call: Call<List<RepositoryModel>?>, t: Throwable) {
                callback.onError(Exception(t))
            }

            override fun onResponse(
                call: Call<List<RepositoryModel>?>,
                response: Response<List<RepositoryModel>?>
            ) {
                callback.onSuccess(response.body())

            }
        })
    }

}