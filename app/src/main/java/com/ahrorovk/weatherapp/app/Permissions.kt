package com.ahrorovk.weatherapp.app

import android.Manifest
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState

//Getting permission for getting current location from device
@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun Permission(onEvent: () -> Unit) {
    var isPermissionGranted by remember { mutableStateOf(false) }

    val requestPermissionLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
            if (isGranted) {
                isPermissionGranted = true
                onEvent.invoke()
            }
        }
    if (!isPermissionGranted) {
        val permissionState =
            rememberPermissionState(Manifest.permission.ACCESS_FINE_LOCATION)
        DisposableEffect(key1 = permissionState) {
            if (permissionState.status.isGranted) {
                onEvent.invoke()
            } else {
                requestPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
            }
            onDispose { }
        }
    }
}