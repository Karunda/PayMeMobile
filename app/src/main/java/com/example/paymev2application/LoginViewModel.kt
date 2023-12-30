package com.example.paymev2application

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.paymev2application.data.LoginRequest
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    fun signin(apiService: ApiService){
        viewModelScope.launch {
            try {
                val response = apiService.login(LoginRequest("superadmin", "123456"))
                if (response.isSuccessful) {
                   Log.d("ViewModel", response.message())
                } else {
                    Log.d("ViewModel", response.message())
                }
            } catch (e: Exception) {
                Log.d("ViewModel", e.message.toString())
            } finally {
                Log.d("ViewModel", "We should reset the state")
            }
        }
    }
}