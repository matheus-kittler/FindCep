package com.example.findcep.view.main

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import com.example.findcep.R
import com.example.findcep.adapter.AddressAdapter
import com.example.findcep.model.Address
import com.example.findcep.view.address.AddressActivity
import com.example.findcep.view.edit.AddressEditActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    val lazyAdapter: AddressAdapter by lazy {
        AddressAdapter(this, object : AddressAdapter.OnAddressClickListener {
            override fun onAddressClick(address: Address, index: Int) {
                val intent = Intent(this@MainActivity, AddressEditActivity::class.java)
                intent.putExtra("address", address)
                intent.putExtra("index", index)
                startActivityForResult(intent, 20)
            }
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setTitle("")


        rvCEP.adapter = lazyAdapter
        rvCEP.layoutManager = (LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false))
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, address: Intent?) {
        super.onActivityResult(requestCode, resultCode, address)

        if (requestCode == 10) {
            if (address != null) {
                val a: Address
                a = address.getSerializableExtra("address") as Address
                lazyAdapter.addAddress(a)
            }
        }

        if (requestCode == 20 && resultCode == Activity.RESULT_OK) {
            if (address != null) {
                val a: Address
                a = address.getSerializableExtra("address") as Address
                lazyAdapter.editAddress(a, address.getIntExtra("index", 0))
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem) : Boolean {
        if (item.itemId == R.id.menu_add) {
            completLoginProcess()
        }
        return super.onOptionsItemSelected(item)
    }

    fun completLoginProcess() {
        val intent = Intent(this@MainActivity, AddressActivity::class.java)
        startActivityForResult(intent, 10)
    }
}


