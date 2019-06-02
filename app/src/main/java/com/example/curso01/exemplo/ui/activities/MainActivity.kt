package com.example.curso01.exemplo.ui.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.Theme
import com.example.curso01.exemplo.R
import com.example.curso01.exemplo.model.Account
import com.example.curso01.exemplo.service.RetrofitInitializer
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonSubmit.setOnClickListener {

            if (isEmailValid(inputLogin.text.toString()) && inputSenha.text.toString() !== "") {

                login(inputLogin.text.toString(), inputSenha.text.toString())

            } else Toast.makeText(this, "Ops Daise, Favor preencher os campos em branco!", Toast.LENGTH_LONG).show()
        }


    }

    private fun isEmailValid(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun login(email: String, password: String) {
        var sa = RetrofitInitializer().serviceAuth()
        var account = Account()

        account.email = email
        account.password = password

        var call = sa.auth()

        var intent = Intent(this, ListActivity::class.java)

        call.enqueue(object : Callback<Account> {
            override fun onFailure(call: Call<Account>?, t: Throwable?) {
                Toast.makeText(this@MainActivity, "Ops", Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<Account>?, response: Response<Account>?) {
                response?.let {
                    if (it.code() == 200) {
                        startActivity(intent)
                    } else {
                        MaterialDialog.Builder(this@MainActivity)
                            .theme(Theme.LIGHT)
                            .title(R.string.mainactivity_ops)
                            .content(R.string.mainactivity_user_pass_invalid)
                            .positiveText(R.string.mainactivity_ok)
                            .show()
                    }
                }
            }
        })
    }
}
