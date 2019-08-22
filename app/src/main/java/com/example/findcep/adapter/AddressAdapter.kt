package com.example.findcep.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.findcep.R
import com.example.findcep.model.Address
import kotlinx.android.synthetic.main.row_cep.view.*

class AddressAdapter : RecyclerView.Adapter<AddressAdapter.AddressViewHolder> {

    var context: Context
    var addresses: MutableList<Address>
    var inflater: LayoutInflater
    var onAddressClickListener: OnAddressClickListener

    constructor(context: Context, onAddressClickListener: OnAddressClickListener) : super() {
        this.context = context
        this.addresses = ArrayList()
        this.inflater = LayoutInflater.from(context)
        this.onAddressClickListener = onAddressClickListener
    }

    fun editAddress(address: Address, index: Int) {
        addresses.set(index, address)
        notifyDataSetChanged()
    }

    fun addAddress(address: Address) {
        addresses.add(address)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): AddressViewHolder {
      return AddressViewHolder(inflater.inflate(R.layout.row_cep, viewGroup, false))
    }
    override fun getItemCount(): Int {
        if (addresses != null) {
            return addresses.size
        }

        return 0
    }

    override fun onBindViewHolder(holder: AddressViewHolder, position: Int) {
        val address = addresses[position]

        holder.itemView.tvCEP.setText(address.cep)
        holder.itemView.tvComplement.setText(address.complemento)
        holder.itemView.tvPublicPlace.setText(address.logradouro)
        holder.itemView.tvNeighborhood.setText(address.bairro)
        holder.itemView.tvLocal.setText(address.localidade)
        holder.itemView.tvUF.setText(address.uf)

        holder.itemView.setOnClickListener{
            if (holder != null) {
                onAddressClickListener.onAddressClick(address, position)
            }
        }
    }

    class AddressViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    interface OnAddressClickListener {
        fun onAddressClick(address: Address, index: Int)
    }
}

