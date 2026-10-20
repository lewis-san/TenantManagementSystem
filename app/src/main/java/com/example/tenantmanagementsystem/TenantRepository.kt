package com.example.tenantmanagementsystem

// An object has exactly ONE instance, shared by the whole app,
// so every screen sees the same list of tenants
object TenantRepository {

    val tenants = mutableListOf<Tenant>()

    fun addTenant(tenant: Tenant) {
        tenants.add(tenant)
    }

    // Convert each rent from text to a number; invalid text counts as 0.0
    fun totalRent(): Double {
        return tenants.sumOf { it.rent.toDoubleOrNull() ?: 0.0 }
    }
}
