package com.gapps.bluetooth.presentation

import com.gapps.bluetooth.domain.chat.BluetoothDevice
import com.gapps.bluetooth.domain.chat.BluetoothMessage

data class BluetoothUiState(
    val scannedDevices: List<BluetoothDevice> = emptyList(),
    val pairedDevices: List<BluetoothDevice> = emptyList(),
    val isConnected: Boolean = false,
    val isConnecting: Boolean = false,
    val errorMessage: String? = null,
    val isBluetoothCommand: Boolean = false,
    val messages: List<BluetoothMessage> = emptyList()
)
