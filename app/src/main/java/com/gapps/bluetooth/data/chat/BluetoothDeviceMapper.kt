package com.gapps.bluetooth.data.chat

import android.annotation.SuppressLint
import android.bluetooth.BluetoothDevice
import com.gapps.bluetooth.domain.chat.BluetoothDeviceDomain

@SuppressLint("MissingPermission")
fun BluetoothDevice.toBluetoothDeviceDomain(): BluetoothDeviceDomain {
    return BluetoothDeviceDomain(
        name = name,
        address = address
    )
}