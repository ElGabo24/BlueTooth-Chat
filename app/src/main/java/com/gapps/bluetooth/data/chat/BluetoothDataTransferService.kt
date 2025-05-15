package com.gapps.bluetooth.data.chat

import android.annotation.SuppressLint
import android.bluetooth.BluetoothSocket
import android.util.Log
import com.gapps.bluetooth.domain.chat.BluetoothMessage
import com.gapps.bluetooth.domain.chat.TransferFailedException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import java.io.IOException

class BluetoothDataTransferService(
    private val socket: BluetoothSocket,
) {
    fun listenForIncomingMessages(): Flow<BluetoothMessage> {
        return flow {
            if (!socket.isConnected) return@flow
            val buffer = ByteArray(1024)
            while (true) {
                val byteCount = try {
                    socket.inputStream.read(buffer)
                } catch (_: Exception) {
                    throw TransferFailedException()
                }
                Log.i("listenForIncomingMessages", "listenForIncomingMessages: ${buffer.decodeToString(
                    endIndex = byteCount
                )}")
                emit(
                    buffer.decodeToString(
                        endIndex = byteCount
                    ).toBluetoothMessage(
                        isFromLocalUser = false
                    )
                )
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun sendMessage(bytes: ByteArray): Boolean {
        return withContext(Dispatchers.IO) {
            try {
                socket.outputStream.write(bytes)
            } catch (e: IOException) {
                e.printStackTrace()
                return@withContext false
            }

            true
        }
    }

    fun sendCommand(message: String) {
        val fullMessage = "$message\r\n"
        try {
        socket.outputStream.write(fullMessage.uppercase().toByteArray(Charsets.UTF_8))
        socket.outputStream.flush()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }


    @SuppressLint("MissingPermission")
    fun readNextMessage(): Flow<BluetoothMessage> = flow {
        if (!socket.isConnected) return@flow

        val reader = socket.inputStream.bufferedReader()

        while (true) {
            val line = try {
                reader.readLine() ?: break
            } catch (_: Exception) {
                throw TransferFailedException()
            }

            Log.i("readNextMessage", "readNextMessage: $line")

            emit(
                BluetoothMessage(
                    message = line.trim(),
                    senderName = socket.remoteDevice.name,
                    isFromLocalUser = false
                )
            )
        }
    }.flowOn(Dispatchers.IO)

}