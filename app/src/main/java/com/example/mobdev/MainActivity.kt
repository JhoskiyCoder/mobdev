package com.example.mobdev

import android.Manifest
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mobdev.mvvm.LocationViewModel
import androidx.compose.ui.platform.LocalContext
import androidx.activity.compose.rememberLauncherForActivityResult


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val context = LocalContext.current
            val locationUtils = remember { LocationUtils(context) }
            val viewModel: LocationViewModel = viewModel()

            LocationDisplay(locationUtils = locationUtils, viewModel = viewModel)
        }
    }
}

@Composable
fun LocationDisplay(locationUtils: LocationUtils, viewModel: LocationViewModel) {
    val location = viewModel.location.value
    val requestPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions(),
        onResult = { permissions ->
            if (permissions[Manifest.permission.ACCESS_FINE_LOCATION] == true &&
                permissions[Manifest.permission.ACCESS_COARSE_LOCATION] == true
            ) {
                locationUtils.requestLocationUpdates(viewModel)
            } else {
                Toast.makeText(
                    locationUtils.context,
                    "Location permission required",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    )

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = {
            if (locationUtils.hasLocationPermission()) {
                locationUtils.requestLocationUpdates(viewModel)
            } else {
                requestPermissionLauncher.launch(
                    arrayOf(
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    )
                )
            }
        }) {
            Text("Get Location")
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (location != null) {
            val address = locationUtils.reverseGeocodeLocation(location)
            Text("Latitude: ${location.latitude}, Longitude: ${location.longitude}")
            Text("Address: $address")
        } else {
            Text("Location not available")
        }
    }
}