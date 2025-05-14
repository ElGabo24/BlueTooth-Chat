package com.gapps.bluetooth.domain.chat

import java.io.IOException

class TransferFailedException: IOException("Reading incoming message failed")