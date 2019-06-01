package com.example.curso01.exemplo.ui.Activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.curso01.exemplo.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var password = "123"

        buttonSubmit.setOnClickListener {

            if (isEmailValid(inputLogin.text.toString()) && inputSenha.text.toString() !== password) {
                Toast.makeText(this,  "Success", Toast.LENGTH_LONG).show()
                val intent = Intent(this, ListActivity::class.java)

                startActivity(intent)
            }

            else Toast.makeText(this,  "Ops Daise", Toast.LENGTH_LONG).show()
        }
    }

    private fun isEmailValid(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}
