package com.example.tenantmanagementsystem

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.tenantmanagementsystem.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var lastTenant: Tenant? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Try It Yourself: Toast user's email passed from LoginActivity
        val userEmail = intent.getStringExtra("USER_EMAIL")
        if (!userEmail.isNullOrEmpty()) {
            Toast.makeText(this, "Logged in as $userEmail", Toast.LENGTH_SHORT).show()
        }

        binding.saveButton.setOnClickListener {
            val name = binding.tenantNameEditText.text.toString().trim()
            val phone = binding.phoneEditText.text.toString().trim()
            val rent = binding.rentEditText.text.toString().trim()

            // Try It Yourself: Check if any field is empty
            if (name.isEmpty() || phone.isEmpty() || rent.isEmpty()) {
                if (name.isEmpty()) binding.tenantNameEditText.error = "Required"
                if (phone.isEmpty()) binding.phoneEditText.error = "Required"
                if (rent.isEmpty()) binding.rentEditText.error = "Required"
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val tenant = Tenant(name, phone, rent)
            binding.tenant = tenant
            lastTenant = tenant

            // Clear input fields
            binding.tenantNameEditText.text.clear()
            binding.phoneEditText.text.clear()
            binding.rentEditText.text.clear()
        }

        binding.callButton.setOnClickListener {
            val tenant = lastTenant
            if (tenant == null) {
                Toast.makeText(this, "Save a tenant first", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:${tenant.phone}"))
            startActivity(intent)
        }

        // Try It Yourself: Share tenant details via Intent.ACTION_SEND
        binding.shareButton.setOnClickListener {
            val tenant = lastTenant
            if (tenant == null) {
                Toast.makeText(this, "Save a tenant first", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val intent = Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                putExtra(Intent.EXTRA_TEXT, tenant.summary())
            }
            startActivity(Intent.createChooser(intent, "Share tenant"))
        }
    }
}