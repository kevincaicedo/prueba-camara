package com.prueba.galery.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.prueba.galery.model.Photo

class RecyclerPhotoAdapter(var listViewModel: ListViewModel, var resource: Int) :  RecyclerView.Adapter<RecyclerPhotoAdapter.CardPhotoHolder>(){

    open var photos : List<Photo>? = null

    fun setCouponsList(coupons: List<Photo>?){
        this.photos = coupons
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): CardPhotoHolder {
        var layoutInflater : LayoutInflater = LayoutInflater.from(p0.context)
        var bindig : ViewDataBinding = DataBindingUtil.inflate(layoutInflater,p1,p0, false)
        return  CardPhotoHolder(bindig)
    }

    override fun getItemCount(): Int {
        return photos?.size ?: 0
    }

    override fun onBindViewHolder(p0: CardPhotoHolder, p1: Int) {
        p0.setDataCard(listViewModel, p1)
    }

    override fun getItemViewType(position: Int): Int {
        return getLayoutForPosition(position)
    }

    fun getLayoutForPosition(position: Int):Int{
        return resource
    }

    class CardPhotoHolder(binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {

        private var binding: ViewDataBinding? = null

        init {
            this.binding = binding
        }

        fun setDataCard(listViewModel: ListViewModel, position: Int){
            /*binding?.setVariable(BR.model, couponViewModel)
            binding?.setVariable(BR.position, position)*/
            binding?.executePendingBindings()
        }


    }

}