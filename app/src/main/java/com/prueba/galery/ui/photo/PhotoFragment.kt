package com.prueba.galery.ui.photo

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.prueba.galery.AppExecutors

import com.prueba.galery.databinding.PhotoFragmentBinding
import dagger.android.support.AndroidSupportInjection
import java.io.File
import javax.inject.Inject

class PhotoFragment : Fragment() {

    companion object {
        fun newInstance() = PhotoFragment()
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: PhotoViewModel by viewModels {
        viewModelFactory
    }

    val args: PhotoFragmentArgs by navArgs()

    private lateinit var binding: PhotoFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = PhotoFragmentBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val path = args.imagePath
        showImage(path)
        goToSaveImage(path)
        cancelAction()
    }

    fun goToSaveImage(path: String){
        binding.button3.setOnClickListener {
            findNavController().navigate(PhotoFragmentDirections.actionPhotoFragmentToFormFragment(path))
        }
    }

    fun cancelAction(){
        binding.button4.setOnClickListener {

            val file = File(args.imagePath)
            val deleted: Boolean = file.delete()

            if(deleted) {
                findNavController().popBackStack(PhotoFragmentDirections.actionPhotoFragmentToMenuFragment().actionId, true)
                findNavController().navigate(PhotoFragmentDirections.actionPhotoFragmentToMenuFragment())
            } else
                Toast.makeText(context, "No se pudo eliminar la imagen", Toast.LENGTH_LONG).show()
        }
    }

    fun showImage(path: String){
        binding.imageView.setImageBitmap(viewModel.getBitmap(path))
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

}
