package com.example.curso01.exemplo.ui.fragments

import android.content.Context
import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.curso01.exemplo.R
import com.example.curso01.exemplo.ui.activities.ListActivity
import com.example.curso01.exemplo.util.DownloadAndSaveImageTask
import kotlinx.android.synthetic.main.layout_image_fragment.*

class ImageFragment: Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Glide.with(activity).load(url_img).into(imgResponse)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            // Check run time permission for write external storage
            // android.permission.WRITE_EXTERNAL_STORAGE

            activity?.let{
                Toast.makeText(it, "Permita os acessos para salvar imagem!!!", Toast.LENGTH_LONG).show()
                val intent = Intent (it, ListActivity::class.java)
                it.startActivity(intent)
            }
        }

        btnCancelImg.setOnClickListener {
            activity?.let{
                val intent = Intent (it, ListActivity::class.java)
                it.startActivity(intent)
            }
        }

        btnSaveImg.setOnClickListener {

            activity?.let{
                DownloadAndSaveImageTask(it).execute(url_img)
                val intent = Intent (it, ListActivity::class.java)
                it.startActivity(intent)
            }
        }
    }

    companion object {
        fun newInstance(url: String): ImageFragment {
            val fragment = ImageFragment()
            val args = Bundle()
            args.putString("url", url)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.layout_image_fragment, container, false)
    }

    override fun onPause() {
        super.onPause()
    }

    private var url_img = ""

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        arguments?.getString("url")?.let {
            url_img = it
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onDetach() {
        super.onDetach()
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onStop() {
        super.onStop()
    }
}