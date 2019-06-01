package com.example.curso01.exemplo.ui.Activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.curso01.exemplo.R
import com.example.curso01.exemplo.model.ListTeam
import com.example.curso01.exemplo.service.RetrofitInitializer
import kotlinx.android.synthetic.main.activity_list.*
import retrofit2.Call
import retrofit2.Response

class ListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        getTeams()

        // lista.adapter = ListAdapter(this, )
    }

    fun getTeams() {
        var s = RetrofitInitializer().serviceTeam()

        var call = s.getTeam()

        call.enqueue(object : retrofit2.Callback<ListTeam>{
            override fun onFailure(call: Call<ListTeam>?, t: Throwable?) {
                Toast.makeText(this@ListActivity, "Falhou favor contatar o Webmaster", Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<ListTeam>?, response: Response<ListTeam>?) {
                response.let {
                    if (it!!.code() == 200) {

                        list.adapter = ListAdapter(
                            this@ListActivity,
                            it.body().lista
                        )
                    } else {
                        Toast.makeText(this@ListActivity, "Deu pau", Toast.LENGTH_LONG).show()
                    }
                }
            }
        })
    }
}
