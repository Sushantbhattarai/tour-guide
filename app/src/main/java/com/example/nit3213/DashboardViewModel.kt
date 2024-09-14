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
class DashboardViewModel @Inject constructor(private val apiService: ApiService) : ViewModel() {
    private val _dashboardData = MutableLiveData<Response<DashboardResponse>>()
    val dashboardData: LiveData<Response<DashboardResponse>> = _dashboardData

    fun getDashboard(keypass: String) {
        viewModelScope.launch {
            val response = apiService.getDashboard(keypass)
            _dashboardData.postValue(response)
        }
    }
}
