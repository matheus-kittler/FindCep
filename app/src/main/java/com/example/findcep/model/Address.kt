package com.example.findcep.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Address : Serializable {

    @SerializedName("cep")
    var cep: String? = null
    @SerializedName("logradouro")
    var logradouro: String? = null
    @SerializedName("complemento")
    var complemento: String? = null
    @SerializedName("bairro")
    var bairro: String? = null
    @SerializedName("localidade")
    var localidade: String? = null
    @SerializedName("uf")
    var uf: String? = null

    constructor(
        cep: String?,
        logradouro: String?,
        complemento: String?,
        bairro: String?,
        localidade: String?,
        uf: String?
    ) {
        this.cep = cep
        this.logradouro = logradouro
        this.complemento = complemento
        this.bairro = bairro
        this.localidade = localidade
        this.uf = uf
    }

    override fun toString(): String {
        return super.toString() as String //não faço ideia mas funcionou no ActivityEdit
    }
}