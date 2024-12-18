package com.example.mobdev


import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.example.mobdev.mvvm.LocationViewModel
import com.example.mobdev.mvvm.LocationData

class LocationUtils(val context: Context) {
    // Проверка разрешений
    fun hasLocationPermission(): Boolean {
        return ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED
    }

    private val _fusedLocationClient: FusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(context)

    // Запрос обновлений местоположения
    @Suppress("MissingPermission")
    fun requestLocationUpdates(viewModel: LocationViewModel) {
        val locationRequest = com.google.android.gms.location.LocationRequest.Builder(1000)
            .setPriority(com.google.android.gms.location.Priority.PRIORITY_HIGH_ACCURACY)
            .build()

        val locationCallback = object : com.google.android.gms.location.LocationCallback() {
            override fun onLocationResult(locationResult: com.google.android.gms.location.LocationResult) {
                locationResult.lastLocation?.let {
                    val locationData = LocationData(it.latitude, it.longitude)
                    viewModel.updateLocation(locationData)
                }
            }
        }
        _fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, android.os.Looper.getMainLooper())
    }

    // Обратное геокодирование
    fun reverseGeocodeLocation(location: LocationData): String {
        val geocoder = android.location.Geocoder(context, java.util.Locale.getDefault())
        val addresses = geocoder.getFromLocation(location.latitude, location.longitude, 1)
        return addresses?.firstOrNull()?.getAddressLine(0) ?: "Address not found"
    }
}