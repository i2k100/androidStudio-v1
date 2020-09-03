package com.myappcompany.imran.sanpchat

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import java.net.HttpURLConnection
import java.net.URL

class ViewSnapActivity : AppCompatActivity() {

    var messageTextView: TextView? = null
    var snapImageView: ImageView? = null

    val auth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_snap)

        messageTextView = findViewById(R.id.messageTextView)
        snapImageView = findViewById(R.id.snapImageView)

        messageTextView?.text = intent.getStringExtra("message")

        //intent.getStringExtra("message")?.let { Log.i("message", it) }

        val task = ImageDownloader()
        val myImage: Bitmap
        try {
            myImage =
                task.execute(intent.getStringExtra("imageURL"))
                    .get()!!
            snapImageView?.setImageBitmap(myImage)
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
    }


    inner class ImageDownloader : AsyncTask<String?, Void?, Bitmap?>() {
        override fun doInBackground(vararg p0: String?): Bitmap? {
        //protected override fun doInBackground(vararg urls: String): Bitmap? {
            return try {
                val url = URL(p0[0])
                val connection = url.openConnection() as HttpURLConnection
                connection.connect()
                val `in` = connection.inputStream
                BitmapFactory.decodeStream(`in`)
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }

    }

    override fun onBackPressed() {
        super.onBackPressed()
        //we need to delete snap child and from images
        auth.currentUser?.uid?.let {
            intent.getStringExtra("snapKey")?.let { it1 ->
                FirebaseDatabase.getInstance().getReference().child("users").child(
                    it
                ).child("snaps").child(it1).removeValue()
            }
        }

        intent.getStringExtra("imageName")?.let {
            FirebaseStorage.getInstance().getReference().child("images").child(
                it
            ).delete()
        }
    }

}