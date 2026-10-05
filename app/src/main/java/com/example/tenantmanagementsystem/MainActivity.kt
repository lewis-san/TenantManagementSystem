package com.example.tenantmanagementsystem

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tenantmanagementsystem.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflate the binding layout
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set button listener
        binding.saveButton.setOnClickListener {
            val name = binding.tenantNameEditText.text.toString()

            // Step 1 of Try It Yourself: Check if name is empty
            if (name.isBlank()) {
                binding.tenantNameEditText.error = "Tenant name is required"
                return@setOnClickListener
            }

            val phone = binding.phoneEditText.text.toString()
            val rent = binding.rentEditText.text.toString()

            val tenant = Tenant(name, phone, rent)
            binding.tenant = tenant

            // Step 4 of Try It Yourself: Clear input fields after saving
            binding.tenantNameEditText.text.clear()
            binding.phoneEditText.text.clear()
            binding.rentEditText.text.clear()
        }
    }
}