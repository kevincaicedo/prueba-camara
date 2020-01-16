package com.prueba.galery.ui.menu

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.prueba.galery.R
import com.prueba.galery.databinding.MenuFragmentBinding
import com.prueba.galery.viewmodel.NavViewModel
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

    private  val navViewModel: NavViewModel by viewModels {
        viewModelFactory
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: MenuFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.menu_fragment, container, false)
        binding.viewmodel = viewModel
        binding.navviewmodel = navViewModel

        binding.cameraDirection = R.id.action_menuFragment_to_cameraFragment
        binding.listDirection = R.id.action_menuFragment_to_listFragment

        return binding.root
    }

    fun setupBiding(){
        navViewModel.getDestination()?.observe(this, Observer {
            if(it != MenuFragmentDirections.actionMenuFragmentToCameraFragment().actionId)
                navToDestination(it)
            else if(checkOrRequestPermission(activity!!, REQUEST_CAMERA))
                navToDestination(it)
        })
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupBiding()
    }

    fun navToDestination(destinationId: Int){
        findNavController().navigate(destinationId, null)
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
