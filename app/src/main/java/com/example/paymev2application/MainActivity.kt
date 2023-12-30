package com.example.paymev2application

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.paymev2application.interceptor.LoggingInterceptor
import com.example.paymev2application.ui.theme.PayMeV2ApplicationTheme
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : ComponentActivity() {
    val client = OkHttpClient.Builder()
        .addInterceptor(LoggingInterceptor())
        .build()

    private val apiService: ApiService by lazy {



        Retrofit.Builder()
            .baseUrl("https://216.98.8.233:8049/api/Authenticate/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(ApiService::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            LoginScreen(apiService = apiService)
        }
    }
}
