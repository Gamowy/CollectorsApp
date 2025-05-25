package org.example.collectorsapp.utils

import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asComposeImageBitmap
import org.jetbrains.skia.Bitmap
import org.jetbrains.skia.Image

actual fun byteArrayToImageBitmap(byteArray: ByteArray): ImageBitmap {
    return Bitmap.makeFromImage(Image.makeFromEncoded(byteArray)).asComposeImageBitmap()
}