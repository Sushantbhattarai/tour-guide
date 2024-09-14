package com.example.nit3213
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.nit3213.databinding.ActivityLoginBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loginButton.setOnClickListener {
            val username = binding.usernameInput.text.toString()
            val password = binding.passwordInput.text.toString()

            if (username.isNotEmpty() && password.isNotEmpty()) {
                viewModel.login(username, password)
            } else {
                Toast.makeText(this, "Enter valid credentials", Toast.LENGTH_SHORT).show()
            }
        }

        viewModel.loginResult.observe(this) { result ->
            if (result != null) {
                if (result.isSuccessful) {
                    val keypass = result.body()?.keypass
                    if (keypass != null) {
                        val intent = Intent(this, DashboardActivity::class.java)
                        intent.putExtra("keypass", keypass)
                        startActivity(intent)
                    }
                } else {
                    Toast.makeText(this, "Login failed", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
