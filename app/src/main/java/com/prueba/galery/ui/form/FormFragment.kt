package com.prueba.galery.ui.form

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.prueba.galery.AppExecutors
import com.prueba.galery.databinding.FormFragmentBinding
import com.prueba.galery.model.Photo
import com.prueba.galery.ui.photo.PhotoFragmentArgs
import dagger.android.support.AndroidSupportInjection
import java.io.File
import javax.inject.Inject


class FormFragment : Fragment() {

    companion object {
        fun newInstance() = FormFragment()
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: FormViewModel by viewModels {
        viewModelFactory
    }

    val args: PhotoFragmentArgs by navArgs()

    @Inject
    lateinit var appExecutors: AppExecutors

    private lateinit var binding: FormFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FormFragmentBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        onSaveAction()
        onCancelAction()
        showImage()
    }

    fun showImage(){
        binding.imageView.setImageBitmap(viewModel.getBitmap(args.imagePath))
    }

    fun onSaveAction(){
        binding.button3.setOnClickListener {
            val name = binding.nombre.text.toString()
            val description = binding.descriptio.text.toString()
            val imagePath = args.imagePath
            val photo = Photo(0, imageUrl = imagePath, name = name, description = description)

            appExecutors.diskIO().execute {
                viewModel.savePhoto(photo)
            }
            findNavController().navigate(FormFragmentDirections.actionFormFragmentToMenuFragment())
        }
    }

    fun onCancelAction(){
        binding.button4.setOnClickListener {
            val file = File(args.imagePath)
            val deleted: Boolean = file.delete()

            if(deleted)
                findNavController().navigate(FormFragmentDirections.actionFormFragmentToMenuFragment())
            else
                Toast.makeText(context, "No se pudo eliminar la imagen", Toast.LENGTH_LONG).show()
        }
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

}
