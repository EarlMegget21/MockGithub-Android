package com.idean.mockgithubandroid

import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface MockGithubApiService {

    @GET("users/{name}")
    fun getUser(
        @Path("name") name: String
    ): Deferred<Response<User>>

    @GET("users/{name}/repos")
    fun getRepoList(
        @Path("name") name: String
    ): Deferred<Response<List<Repo>>>
}

