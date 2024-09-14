package com.example.nit3213

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class DashboardActivity : AppCompatActivity() {

    @Inject
    lateinit var apiService: ApiService
    private lateinit var recyclerView: RecyclerView
    private lateinit var dashboardAdapter: DashboardAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        dashboardAdapter = DashboardAdapter { destination ->
            val intent = Intent(this, DetailsActivity::class.java)
            intent.putExtra("destination", destination)
            startActivity(intent)
        }
        recyclerView.adapter = dashboardAdapter

        lifecycleScope.launch {
            try {
                val response = apiService.getDashboard("travel")
                if (response.isSuccessful) {
                    response.body()?.let {
                        dashboardAdapter.submitList(it.entities)
                    }
                } else {
                    Log.e("Dashboard", "Error: ${response.errorBody()?.string()}")
                }
            } catch (e: Exception) {
                Log.e("Dashboard", "Exception: ${e.message}")
            }
        }
    }
}
