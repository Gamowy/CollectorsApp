package org.example.collectorsapp.utils

import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asComposeImageBitmap
import androidx.compose.ui.graphics.asSkiaBitmap
import org.jetbrains.skia.Bitmap
import org.jetbrains.skia.Image

actual fun byteArrayToImageBitmap(byteArray: ByteArray): ImageBitmap {
    return Bitmap.makeFromImage(Image.makeFromEncoded(byteArray)).asComposeImageBitmap()
}

actual fun ImageBitmap.encodeToPngBytes(quality: Int): ByteArray? {
    return Image.makeFromBitmap(this.asSkiaBitmap()).encodeToData(org.jetbrains.skia.EncodedImageFormat.PNG, quality)?.bytes
}