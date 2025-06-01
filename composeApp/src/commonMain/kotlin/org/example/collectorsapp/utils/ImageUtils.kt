package org.example.collectorsapp.utils

import androidx.compose.ui.graphics.ImageBitmap
import com.preat.peekaboo.image.picker.toImageBitmap

expect fun byteArrayToImageBitmap(byteArray: ByteArray): ImageBitmap

expect fun ImageBitmap.encodeToPngBytes(quality: Int = 100) : ByteArray?

fun processImage(image: ByteArray): ByteArray? {
    val imageBitmap = image.toImageBitmap()
    return imageBitmap.encodeToPngBytes(5)
}