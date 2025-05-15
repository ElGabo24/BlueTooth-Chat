package com.gapps.bluetooth.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemGesturesPadding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gapps.bluetooth.domain.chat.BluetoothDevice
import com.gapps.bluetooth.presentation.components.BluetoothDeviceList
import com.gapps.bluetooth.ui.theme.BluetoothTheme

@Composable
fun DeviceScreen(
    state: BluetoothUiState,
    onStartScan: () -> Unit,
    onStopScan: () -> Unit,
    onStartServer: () -> Unit,
    onDeviceClick: (BluetoothDevice) -> Unit,
    onCheckBluetothCommand: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .systemGesturesPadding()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            BluetoothDeviceList(
                modifier = Modifier
                    .fillMaxSize(),
                pairedDevices = state.pairedDevices,
                scannedDevices = state.scannedDevices,
                onClick = {
                    onDeviceClick(it)
                },
            )
            Row(
                modifier = Modifier
                    .padding(top = 8.dp)
                    .padding(end = 16.dp)
                    .align(Alignment.TopEnd),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = "Device",
                    style = MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.SemiBold
                )
                Switch(
                    checked = state.isBluetoothCommand,
                    onCheckedChange = {
                        onCheckBluetothCommand()
                    }
                )
                Text(
                    text = "Commando",
                    style = MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
        
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

@Preview(showBackground = true)
@Composable
private fun PreviewDeviceScreen() {
    BluetoothTheme {
        DeviceScreen(
            state = BluetoothUiState(),
            onStartScan = {},
            onStopScan = {},
            onStartServer = {},
            onDeviceClick = {},
            onCheckBluetothCommand = {}
        )
    }
}