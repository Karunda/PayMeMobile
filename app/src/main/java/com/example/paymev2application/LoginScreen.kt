package com.example.paymev2application

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.example.paymev2application.data.LoginResponse
import androidx.lifecycle.viewModelScope
import com.example.paymev2application.data.LoginRequest
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(apiService: ApiService) {
    val context = LocalContext.current
    val emailState = remember { mutableStateOf("") }
    val passwordState = remember { mutableStateOf("") }
    val loadingState = remember { mutableStateOf(false) }
    val loginResultState = remember { mutableStateOf<Result<LoginResponse>?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        TextField(
            value = emailState.value,
            onValueChange = { emailState.value = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = passwordState.value,
            onValueChange = { passwordState.value = it },
            label = { Text("Password") },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                val email = emailState.value
                val password = passwordState.value
                val loginViewModel = LoginViewModel()
                // Perform login
                loadingState.value = true
                loginViewModel.signin(apiService)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            Text(text = "Login")
        }

        if (loadingState.value) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
        }

        loginResultState.value?.let { result ->
            when {
                result.isSuccess -> {
                    val loginResponse = result.getOrThrow()
                    // Handle successful login response
                    Toast.makeText(context, "Login successful", Toast.LENGTH_SHORT).show()
                }
                result.isFailure -> {
                    val exception = result.exceptionOrNull()
                    // Handle login failure
                    Toast.makeText(context, "Login failed: ${exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}