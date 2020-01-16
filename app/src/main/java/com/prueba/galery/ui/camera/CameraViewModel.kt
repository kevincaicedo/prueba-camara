package com.prueba.galery.ui.camera

import android.graphics.Matrix
import android.view.Surface
import android.view.TextureView
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureConfig
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class CameraViewModel @Inject constructor() : ViewModel() {

    fun getImageCapture(): ImageCapture {
        val imageCaptureConfig = ImageCaptureConfig.Builder()
            .apply {
                setCaptureMode(ImageCapture.CaptureMode.MIN_LATENCY)
            }.build()

        return ImageCapture(imageCaptureConfig)
    }

    fun updateTransform(viewFinder: TextureView) {
        val matrix = Matrix()

        val centerX = viewFinder.width / 2f
        val centerY = viewFinder.height / 2f

        val rotationDegrees = when(viewFinder.display.rotation) {
            Surface.ROTATION_0 -> 0
            Surface.ROTATION_90 -> 90
            Surface.ROTATION_180 -> 180
            Surface.ROTATION_270 -> 270
            else -> return
        }
        matrix.postRotate(-rotationDegrees.toFloat(), centerX, centerY)

        viewFinder.setTransform(matrix)
    }

}
