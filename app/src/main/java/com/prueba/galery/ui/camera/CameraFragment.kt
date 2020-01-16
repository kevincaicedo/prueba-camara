package com.prueba.galery.ui.camera

import android.content.Context
import android.os.Bundle
import android.util.Size
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.camera.core.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.prueba.galery.AppExecutors
import com.prueba.galery.databinding.CameraFragmentBinding
import dagger.android.support.AndroidSupportInjection
import java.io.File
import javax.inject.Inject

class CameraFragment : Fragment(), LifecycleOwner {

    companion object {
        fun newInstance() = CameraFragment()
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val  viewModel: CameraViewModel by viewModels {
        viewModelFactory
    }

    private lateinit var binding: CameraFragmentBinding

    @Inject
    lateinit var appExecutors: AppExecutors

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = CameraFragmentBinding.inflate(inflater, container, false)
        // DataBindingUtil.inflate(inflater, R.layout.camera_fragment, container, false)
        binding.lifecycleOwner = this

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        startCamera()
        onCancel()
    }

    private fun onCancel(){
        binding.button4.setOnClickListener {
            findNavController().popBackStack(CameraFragmentDirections.actionCameraFragmentToMenuFragment().actionId, true)
            findNavController().navigate(CameraFragmentDirections.actionCameraFragmentToMenuFragment())
        }
    }

    private fun startCamera() {
        val previewConfig = PreviewConfig.Builder().apply {
            setTargetResolution(Size(640, 480))
        }.build()

        val preview = Preview(previewConfig)

        preview.setOnPreviewOutputUpdateListener {

            val parent = binding.viewFinder.parent as ViewGroup
            parent.removeView(binding.viewFinder)
            parent.addView(binding.viewFinder, 0)

            binding.viewFinder.surfaceTexture = it.surfaceTexture
            viewModel.updateTransform(binding.viewFinder)
        }

        val imageCapture = viewModel.getImageCapture()
        onNavCapture(imageCapture)
        CameraX.bindToLifecycle(this, preview, imageCapture)

    }

    fun onNavCapture(imageCapture: ImageCapture){

        binding.button3.setOnClickListener {
            takePicture(imageCapture)
        }
    }

    fun takePicture(imageCapture: ImageCapture){
        val file = File(activity?.externalMediaDirs?.first(), "${System.currentTimeMillis()}.jpg")

        imageCapture.takePicture(file, appExecutors.diskIO(),
            object : ImageCapture.OnImageSavedListener {
                override fun onImageSaved(file: File) {
                    findNavController().navigate(CameraFragmentDirections.actionCameraFragmentToPhotoFragment(file.absolutePath))
                    /*binding.viewFinder.post {
                        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
                    }*/
                }

                override fun onError(
                    imageCaptureError: ImageCapture.ImageCaptureError,
                    message: String, exc: Throwable?) {
                    val msg = "Photo capture failed: $message"
                    binding.viewFinder.post {
                        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
                    }
                }
            })
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

}
