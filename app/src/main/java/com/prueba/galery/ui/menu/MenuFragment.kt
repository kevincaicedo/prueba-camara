package com.prueba.galery.ui.menu

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.prueba.galery.R
import com.prueba.galery.databinding.MenuFragmentBinding
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject


class MenuFragment : Fragment() {

    companion object {
        fun newInstance() = MenuFragment()

        const val REQUEST_CAMERA = 1234
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: MenuViewModel by viewModels {
        viewModelFactory
    }

    private lateinit var binding: MenuFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = MenuFragmentBinding.inflate(inflater, container, false)
        binding.viewmodel = viewModel

        return binding.root
    }

    fun setupOnAction(){
        binding.button2.setOnClickListener {
            navToDestination(MenuFragmentDirections.actionMenuFragmentToListFragment().actionId)
        }
        binding.button.setOnClickListener {
            if(checkOrRequestPermission(activity!!, REQUEST_CAMERA))
                navToDestination(MenuFragmentDirections.actionMenuFragmentToCameraFragment().actionId)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupOnAction()
    }

    fun navToDestination(destinationId: Int){
        findNavController().navigate(destinationId)
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            REQUEST_CAMERA -> {

                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    navToDestination(R.id.action_menuFragment_to_cameraFragment)
                } else {
                    Toast.makeText(context, "No puedes aceder a la camara", Toast.LENGTH_LONG).show()
                }
                return
            }
        }
    }

    fun checkOrRequestPermission(activity: FragmentActivity, requestCode: Int): Boolean {

        return if (ContextCompat.checkSelfPermission(activity, Manifest.permission.CAMERA)
            != PackageManager.PERMISSION_GRANTED) {

            requestPermissions(
                arrayOf(Manifest.permission.CAMERA),
                requestCode)
            false
        } else {
            true
        }
    }

}
