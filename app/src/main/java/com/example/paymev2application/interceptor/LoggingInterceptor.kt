package com.example.paymev2application.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor

class LoggingInterceptor : Interceptor {
    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        loggingInterceptor.intercept(chain)

        val response = chain.proceed(request)
        loggingInterceptor.intercept(chain)
        return response
    }
}