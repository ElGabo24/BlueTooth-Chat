package com.gapps.bluetooth.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.systemGesturesPadding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.gapps.bluetooth.domain.chat.BluetoothDevice
import com.gapps.bluetooth.presentation.components.BluetoothDeviceList

@Composable
fun DeviceScreen(
    state: BluetoothUiState,
    onStartScan: () -> Unit,
    onStopScan: () -> Unit,
    onStartServer: () -> Unit,
    onDeviceClick: (BluetoothDevice) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .systemGesturesPadding()
    ) {
        BluetoothDeviceList(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            pairedDevices = state.pairedDevices,
            scannedDevices = state.scannedDevices,
            onClick = {
                onDeviceClick(it)
            },
        )
        
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Button(
                onClick = onStartScan
            ) {
                Text(text = "Start scan")
            }
            Button(
                onClick = onStopScan
            ) {
                Text(text = "Stop scan")
            }
            Button(
                onClick = onStartServer
            ) {
                Text(text = "Start server")
            }
        }
    }
}