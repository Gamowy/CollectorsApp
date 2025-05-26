package org.example.collectorsapp.utils

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.graphics.asImageBitmap
import java.io.ByteArrayOutputStream

actual fun byteArrayToImageBitmap(byteArray: ByteArray): ImageBitmap {
    return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size).asImageBitmap()
}

actual fun ImageBitmap.encodeToPngBytes(quality: Int): ByteArray? {
    ByteArrayOutputStream().use { bytes ->
        this.asAndroidBitmap().compress(Bitmap.CompressFormat.PNG, quality, bytes)
        return bytes.toByteArray()
    }
}