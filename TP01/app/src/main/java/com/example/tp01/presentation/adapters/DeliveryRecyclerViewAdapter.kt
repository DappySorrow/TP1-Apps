package com.example.tp01.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.tp01.databinding.ItemRessourceBinding
import com.example.tp01.domain.models.Delivery

class DeliveryRecyclerViewAdapter() :
    RecyclerView.Adapter<DeliveryRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup,viewType: Int)
            : DeliveryRecyclerViewAdapter.ViewHolder {
        return ViewHolder(
            ItemRessourceBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(
        holder: DeliveryRecyclerViewAdapter.ViewHolder,position: Int) {
        val delivery = differ.currentList[position]
        holder.bind(delivery)
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////

    private val differCallback = object : DiffUtil.ItemCallback<Delivery>(){
        override fun areItemsTheSame(oldItem: Delivery, newItem: Delivery): Boolean {
            return oldItem.idDelivery == newItem.idDelivery
        }

        override fun areContentsTheSame(oldItem: Delivery, newItem: Delivery): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun getItemCount(): Int = differ.currentList.size

    ///////////////////////////////////////////////////////////////////////////////////////////////

    inner class ViewHolder(private val binding: ItemRessourceBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(delivery: Delivery) {
            with(binding)
            {
                txtIaspyx.text = delivery.iaspyx.toString()
                txtBilerium.text = delivery.blierium.toString()
                txtJasmalt.text = delivery.jasmalt.toString()
                txtSmiathil.text = delivery.smiathil.toString()
                txtVethyx.text = delivery.vethyx.toString()
            }

        }
    }


}