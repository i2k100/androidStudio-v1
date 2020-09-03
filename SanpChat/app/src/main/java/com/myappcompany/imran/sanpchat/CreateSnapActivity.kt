package com.myappcompany.imran.sanpchat

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.storage.FirebaseStorage
import java.io.ByteArrayOutputStream
import java.util.*

class CreateSnapActivity : AppCompatActivity() {

    var createSnapImageView: ImageView? = null
    var messageEditText: EditText? = null
    val imageName = UUID.randomUUID().toString() + ".jpg"

    fun getPhoto() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI) //To get the intent for images
        startActivityForResult(intent, 1)
        //Toast.makeText(this, "getPhoto", Toast.LENGTH_SHORT).show()
    }

    fun chooseImageButton(view: View) {
        if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_DENIED) {
            requestPermissions(arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), 1)
        } else {
            //Toast.makeText(this, "Clicked", Toast.LENGTH_SHORT).show()
            getPhoto()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val selectedImage = data!!.data // location of selected image
        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            val selectedPhotoUri = data.data
            try {
                val bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, selectedImage)
                //val bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, selectedPhotoUri)
                createSnapImageView?.setImageBitmap(bitmap)
                //Toast.makeText(this, "onActivityResult", Toast.LENGTH_SHORT).show()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String?>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 1) {
            if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //Toast.makeText(this, "onRequestPermissionsResult", Toast.LENGTH_SHORT).show()
                getPhoto()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_snap)
        createSnapImageView = findViewById(R.id.createSnapImageView)
        messageEditText = findViewById(R.id.messageEditText)
    }

    fun nextClicked(view: View) {
        // Get the data from an ImageView as bytes
        createSnapImageView?.isDrawingCacheEnabled = true
        createSnapImageView?.buildDrawingCache()
        val bitmap = (createSnapImageView?.drawable as BitmapDrawable).bitmap
        val baos = ByteArrayOutputStream()
        bitmap?.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val data = baos.toByteArray()

        //var uploadTask = mountainsRef.putBytes(data)
        var uploadTask = FirebaseStorage.getInstance().getReference().child("images").child(imageName).putBytes(data)
        uploadTask.addOnFailureListener {
            // Handle unsuccessful uploads
            Toast.makeText(this, "Upload failed", Toast.LENGTH_SHORT).show()
        }.addOnSuccessListener {
            // taskSnapshot.metadata contains file metadata such as size, content-type, etc.
            // ...
            //val downloadUrl = taskSnapShot.downloadUrl
            //val downloadUrl = uploadSessionUri.downloadUrl
            var url: String = ""
            var task = it.storage.downloadUrl
            task.addOnSuccessListener {
                url = task.getResult().toString()
                Log.i("URL   ",url)

                //move to next activity
                val intent = Intent(this, ChooseUserActivity::class.java)
                //to provide data to next activity
                intent.putExtra("imageURL", url)
                intent.putExtra("imageName", imageName)
                intent.putExtra("message", messageEditText?.text.toString())
                startActivity(intent)
            }
        }

    }
}