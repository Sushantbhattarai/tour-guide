package com.example.nit3213

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val apiService: ApiService) : ViewModel() {
    private val _loginResult = MutableLiveData<Response<LoginResponse>>()
    val loginResult: LiveData<Response<LoginResponse>> = _loginResult

    fun login(username: String, password: String) {
        viewModelScope.launch {
            val request = LoginRequest(username, password)
            val response = apiService.login(request)
            _loginResult.postValue(response)
        }
    }
}
