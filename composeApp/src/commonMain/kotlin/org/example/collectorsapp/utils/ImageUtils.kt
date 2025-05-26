package org.example.collectorsapp.utils

import androidx.compose.ui.graphics.ImageBitmap

expect fun byteArrayToImageBitmap(byteArray: ByteArray): ImageBitmap

expect fun ImageBitmap.encodeToPngBytes(quality: Int = 100) : ByteArray?