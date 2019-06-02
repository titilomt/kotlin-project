package com.example.curso01.exemplo.ui.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.Theme
import com.bumptech.glide.Glide
import com.example.curso01.exemplo.R
import com.example.curso01.exemplo.util.DownloadAndSaveImageTask
import kotlinx.android.synthetic.main.activity_image_meme.*

class ImageMemeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_meme)

        var url = intent.extras.getString("URL_CREATED")

        var imgMeme = findViewById<ImageView>(R.id.img_create_response)

        Glide.with(this).load(url).into(imgMeme)

        btnSaveImg.setOnClickListener {
            DownloadAndSaveImageTask(this).execute(url)

            var intent = Intent(this, ListActivity::class.java)

            Toast.makeText(this, "Sucesso", Toast.LENGTH_LONG)

            startActivity(intent)
        }

        btnCancelImg.setOnClickListener {
            var intent = Intent(this, ListActivity::class.java)

            startActivity(intent)
        }
    }
}
