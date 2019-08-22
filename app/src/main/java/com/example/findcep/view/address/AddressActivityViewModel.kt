package com.example.findcep.view.address

import com.example.findcep.model.Address
import com.example.findcep.services.IAddressAPI

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.Callback
import retrofit2.Response
import retrofit2.converter.gson.GsonConverterFactory

class AddressActivityViewModel(
    val interaction: AddressActivityInteraction
) {

    var msg: String = "Ocorreu um problema ao carregar o Endereço"

    fun loadAddress(cep: String) {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://viacep.com.br/ws/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                    .build()
            )
            .build()



        retrofit.create(IAddressAPI::class.java).getAddress(cep).enqueue(object : Callback<Address> {
            override fun onFailure(call: Call<Address>?, t: Throwable?) {
                interaction.onError(msg)
            }

            override fun onResponse(call: Call<Address>?, response: Response<Address>?) {
                if (response != null && response.body() != null) {
                    interaction.onLoadCep(response.body())
                } else {
                    interaction.onError(msg)
                }
            }

        })
    }
}



