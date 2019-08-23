package com.example.findcep.view.address

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.findcep.R
import com.example.findcep.model.Address
import com.example.findcep.view.main.MainActivity
import kotlinx.android.synthetic.main.activity_address.*
import kotlinx.android.synthetic.main.activity_address.btnBack
import kotlinx.android.synthetic.main.activity_address.btnSend
import kotlinx.android.synthetic.main.activity_address.clForms
import kotlinx.android.synthetic.main.activity_address.etCep
import kotlinx.android.synthetic.main.activity_address.etNeighborhood
import kotlinx.android.synthetic.main.activity_address.etPublicPlace
import kotlinx.android.synthetic.main.activity_address_edit.*

class AddressActivity : AppCompatActivity(), AddressActivityInteraction {

    lateinit var address: Address
    lateinit var viewModel: AddressActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_address)


        viewModel = AddressActivityViewModel(this)

        etLocalEdit

        btnFind.setOnClickListener {
            loadPage()
            checkFormsForSend()
        }

        btnBack.setOnClickListener {
            finish()
        }

        btnSend.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            address.cep = etCep.text.toString() //TODO faz uma especie de "reload" do que foi digitado mandando o que está no form.
            address.logradouro = etPublicPlace.text.toString()
            address.complemento = etComple.text.toString()
            address.bairro = etNeighborhood.text.toString()
            address.uf = etUf.text.toString()
            intent.putExtra("address", address)
            setResult(Activity.RESULT_OK, intent)
            finish()
        }
    }

    override fun onLoadCep(a: Address) {
        rlLoader.visibility = View.GONE
        clForms.visibility = View.VISIBLE
        address = a
        if (a != null) {
            etPublicPlace.setText(a.logradouro)
            etNeighborhood.setText(a.bairro)
            etLocal.setText(a.localidade) //por algum motivo foi preciso alterar os nomes no xml para setar os do activity_address
            etComple.setText(a.complemento)
            etUf.setText(a.uf)
        }
    }

    override fun onError(msg: String) {
        rlLoader.visibility = View.GONE
    }

    fun loadPage() {
        rlLoader.visibility = View.VISIBLE
        clForms.visibility = View.GONE
        viewModel.loadAddress(etCep.text.toString())
    }

    fun checkFormsForSend() {
        if (etCep != null && etPublicPlace != null && etNeighborhood != null && etLocal != null && etUf != null) {
            btnSend.visibility = View.VISIBLE
            btnBack.visibility = View.VISIBLE
        } else {
            btnSend.visibility = View.GONE
            btnBack.visibility = View.GONE
        }
    }
}
