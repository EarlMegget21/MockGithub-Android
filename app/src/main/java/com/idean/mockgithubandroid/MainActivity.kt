package com.idean.mockgithubandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    companion object {
        val okHttpClient = OkHttpClient.Builder().build()
    }

    val service = Retrofit.Builder()
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .addConverterFactory(GsonConverterFactory.create()) //pour parser le JSON
        .client(okHttpClient)
        .baseUrl("https://"+BuildConfig.urlServer) //domaine de l'API
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
