package com.example.curso01.exemplo.ui.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.Theme
import com.bumptech.glide.Glide
import com.example.curso01.exemplo.R
import com.example.curso01.exemplo.model.CreateMemes
import com.example.curso01.exemplo.model.Memes
import com.example.curso01.exemplo.service.RetrofitInitializer
import com.example.curso01.exemplo.ui.fragments.ImageFragment
import kotlinx.android.synthetic.main.activity_craft_meme.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CraftMemeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_craft_meme)

        var itemMeme = intent.extras.get(ListActivity.EXTRA_MEME) as Memes

        Glide.with(this).load(itemMeme.url).into(imgTemplate)

        btnCraft.setOnClickListener {
            var txt1 = craftTxt1.text.toString()
            var txt2 = craftTxt2.text.toString()
            if(txt1 != "" || txt2 != "") {
                craftMeme(txt1, txt2, itemMeme.id)
            } else {
                MaterialDialog.Builder(this@CraftMemeActivity)
                    .theme(Theme.LIGHT)
                    .title(R.string.craftactivity_ops)
                    .content(R.string.craftactivity_invalid_fields)
                    .positiveText(R.string.craftactivity_ok)
                    .show()
            }
        }
    }

    private fun craftMeme(txt1: String, txt2: String, id: String) {
        var sm = RetrofitInitializer().serviceMemes()

        var call = sm.createMemes(id, "memesforfun3000", "furacao2000", txt1, txt2)

        call.enqueue(object: Callback<CreateMemes>{
            override fun onFailure(call: Call<CreateMemes>?, t: Throwable?) {
                MaterialDialog.Builder(this@CraftMemeActivity)
                    .theme(Theme.LIGHT)
                    .title(R.string.craftactivity_ops)
                    .content(R.string.craftactivity_invalid_response)
                    .positiveText(R.string.craftactivity_ok)
                    .show()
                Log.i("Err", t?.message)
            }

            override fun onResponse(call: Call<CreateMemes>?, response: Response<CreateMemes>?) {
                response.let {
                    if (it!!.code() == 200){

                        val imageFragment = ImageFragment.newInstance(it.body().url)

                        val manager = supportFragmentManager

                        val transaction = manager.beginTransaction()

                        transaction.replace(R.id.fragment_container, imageFragment)
                        transaction.addToBackStack(null)

                        transaction.commit()

                        fragment_container
                    } else {
                        Toast.makeText(this@CraftMemeActivity, "Ahh foi quase...", Toast.LENGTH_LONG).show()
                    }
                }
            }
        })
    }
}
