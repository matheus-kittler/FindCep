package com.example.findcep.view.address

import com.example.findcep.model.Address

interface AddressActivityInteraction {

    fun onLoadCep(address: Address)
    fun onError(msg: String)
}