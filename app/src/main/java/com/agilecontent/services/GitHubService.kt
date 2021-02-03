package com.agilecontent.services

import com.agilecontent.models.RepositoryModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubService {

    @GET("users/{user}/repos")
    fun listRepos(@Path("user") user: String): Call<List<RepositoryModel>>

}