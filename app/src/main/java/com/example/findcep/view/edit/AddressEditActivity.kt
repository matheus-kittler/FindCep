package com.example.findcep.view.edit

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.findcep.R
import com.example.findcep.model.Address
import com.example.findcep.view.main.MainActivity
import kotlinx.android.synthetic.main.activity_address.*
import kotlinx.android.synthetic.main.activity_address.btnBack
import kotlinx.android.synthetic.main.activity_address.btnSend
import kotlinx.android.synthetic.main.activity_address.etCep
import kotlinx.android.synthetic.main.activity_address_edit.*

class AddressEditActivity : AppCompatActivity() {

    lateinit var address: Address

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_address_edit)

        address = intent.getSerializableExtra("address") as Address


            etCepEdit.setText(address.cep)
            etPublicPlaceEdit.setText(address.logradouro)
            etComplementEdit.setText(address.complemento)
            etLocalEdit.setText(address.localidade)
            etUFEdit.setText(address.uf)
            etNeighborhoodEdit.setText(address.bairro)


        btnBack.setOnClickListener {
            finish()
        }

        btnSend.setOnClickListener {
            val intent = Intent(this@AddressEditActivity, MainActivity::class.java)
            address.cep = etCepEdit.text.toString()
            address.complemento = etComplementEdit.text.toString()
            address.logradouro = etPublicPlaceEdit.text.toString()
            address.bairro = etNeighborhoodEdit.text.toString()
            address.localidade = etLocalEdit.text.toString()
            address.uf = etUFEdit.text.toString()
            intent.putExtra("address", address)
            intent.putExtra("index", getIntent().getIntExtra("index", 0))
            setResult(Activity.RESULT_OK, intent)
            finish()
        }
    }
}
