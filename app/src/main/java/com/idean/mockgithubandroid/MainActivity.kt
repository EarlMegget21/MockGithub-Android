package com.idean.mockgithubandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

class MainActivity : AppCompatActivity() {

    companion object {
        val okHttpClient = OkHttpClient.Builder().build()
    }

    val service = Retrofit.Builder()
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .addConverterFactory(GsonConverterFactory.create()) //to parse the JSON
        .client(okHttpClient)
        .baseUrl("https://"+BuildConfig.urlServer) //API domain
        .build()
        .create(MockGithubApiService::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        search.setOnClickListener {
            GlobalScope.launch (Dispatchers.Main) {
                val user = service.getUser(searchText.text.toString()).await()

                name.text = user.body()?.name
            }
        }
    }
}

interface MockGithubApiService {
    @GET("users/{login}")
    fun getUser(
        @Path("login") login: String
    ): Deferred<Response<User>>
}

class User(
    var id: Int,
    var name: String,
    var login: String
)