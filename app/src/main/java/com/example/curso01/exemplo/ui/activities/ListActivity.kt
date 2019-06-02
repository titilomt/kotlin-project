package com.example.curso01.exemplo.ui.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.curso01.exemplo.R
import com.example.curso01.exemplo.model.MainMemes
import com.example.curso01.exemplo.model.Memes
import com.example.curso01.exemplo.service.RetrofitInitializer
import com.example.curso01.exemplo.ui.activities.adapters.ListAdapter
import kotlinx.android.synthetic.main.activity_list.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        getMemes()


    }

    companion object {
        const val EXTRA_MEME = "meme"
    }

    private fun getMemes() {
        var sm = RetrofitInitializer().serviceMemes()

        var call = sm.getMemes()

        var intent = Intent(this, CraftMemeActivity::class.java)

        call.enqueue(object: Callback<MainMemes> {
            override fun onFailure(call: Call<MainMemes>?, t: Throwable?) {
                Toast.makeText(this@ListActivity, "Falhou favor contatar o Webmaster", Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<MainMemes>?, response: Response<MainMemes>?) {
                response.let {
                    if (it!!.code() == 200) {

                        listMemes.adapter = ListAdapter(
                            this@ListActivity,
                            it.body().dataMemes.listMemes
                        )

                        listMemes.setOnItemClickListener { adapterView, view, i, l ->
                            var memes = adapterView.getItemAtPosition(i) as Memes
                            intent.putExtra(EXTRA_MEME, memes)
                            startActivity(intent)
                        }

                    } else {
                        Toast.makeText(this@ListActivity, "Tá pegando fogo bixo...", Toast.LENGTH_LONG).show()
                    }
                }
            }
        })
    }
}
