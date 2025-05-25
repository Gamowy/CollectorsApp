package org.example.collectorsapp.utils

import android.graphics.BitmapFactory
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap

actual fun byteArrayToImageBitmap(byteArray: ByteArray): ImageBitmap {
    return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size).asImageBitmap()
}