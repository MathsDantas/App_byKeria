package com.example.bykeria.data.model

import androidx.lifecycle.ViewModel
import com.example.bykeria.data.network.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CadastroViewModel : ViewModel() {
    fun cadastrar(
        name: String,
        username: String,
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        val user = UserRequest(name, username, email, password, "admin")

        RetrofitInstance.api.createUser(user).enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    onSuccess()
                } else {
                    onError("Erro ao criar conta: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                onError("Erro de conexão: ${t.localizedMessage}")
            }
        })
    }
}
