package com.example.paymev2application

import com.example.paymev2application.data.LoginRequest
import com.example.paymev2application.data.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("loginweb")
    suspend fun login(
        @Body loginRequest: LoginRequest
    ): Response<LoginResponse>
}