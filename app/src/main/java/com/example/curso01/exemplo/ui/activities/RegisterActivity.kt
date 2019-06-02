package com.example.curso01.exemplo.ui.activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.Theme
import com.example.curso01.exemplo.R
import com.example.curso01.exemplo.model.Account
import com.example.curso01.exemplo.service.RetrofitInitializer
import kotlinx.android.synthetic.main.activity_register.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        btnCrud.setOnClickListener {
            var name = crudName.text.toString()
            var email = crudEmail.text.toString()
            var password = crudPassword.text.toString()
            var confirmPassword = crudConfirmPassword.text.toString()

            if(validateFields(name, email, password, confirmPassword)) {
                if (password == confirmPassword) {
                    signUp(name, email, password)
                } else {
                    MaterialDialog.Builder(this@RegisterActivity)
                        .theme(Theme.LIGHT)
                        .title(R.string.registeractivity_ops)
                        .content(R.string.registeractivity_user_pass_invalid)
                        .positiveText(R.string.registeractivity_ok)
                        .show()
                }
            } else {
                MaterialDialog.Builder(this@RegisterActivity)
                    .theme(Theme.LIGHT)
                    .title(R.string.registeractivity_ops)
                    .content(R.string.registeractivity_invalid_fields)
                    .positiveText(R.string.registeractivity_ok)
                    .show()
            }
        }
    }

    fun signUp(name: String, email: String, password: String){
        var sa = RetrofitInitializer().serviceAuth()

        var account = Account()

        account.name = name
        account.email = email
        account.password = password

        var intent = Intent(this, MainActivity::class.java)

        var call = sa.register(account)

        call.enqueue(object: Callback<Void>{
            override fun onFailure(call: Call<Void>?, t: Throwable?) {
                Toast.makeText(this@RegisterActivity, "Ops", Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<Void>?, response: Response<Void>?) {
                response?.let {
                    if (it.code() == 200) {
                        Toast.makeText(this@RegisterActivity, "Success", Toast.LENGTH_LONG).show()
                        startActivity(intent)
                    } else {
                        MaterialDialog.Builder(this@RegisterActivity)
                            .theme(Theme.LIGHT)
                            .title(R.string.registeractivity_ops)
                            .content(R.string.registeractivity_email_invalid)
                            .positiveText(R.string.registeractivity_ok)
                            .show()
                    }
                }
            }
        })
    }

    private fun validateFields(name: String, email: String, password: String, confirmPassword: String): Boolean{

        return (isEmailValid(email) && name != "" && password != "" && confirmPassword!= "")
    }

    private fun isEmailValid(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}
