package com.example.firestoragedemo

import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.example.firestoragedemo.databinding.ActivityMainBinding
import com.google.firebase.storage.FirebaseStorage
//import com.google.firebase.storage.FirebaseStorage
import java.io.File

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val ref = FirebaseStorage.getInstance().reference

        binding.btnBrowse.setOnClickListener(){

            startForResult.launch("image/*")
        }


        binding.btnUpload.setOnClickListener(){

        }

        binding.btnGet.setOnClickListener(){

            val imgRef = ref.child("temp.png")
            val file = File.createTempFile("temp", "png")

            imgRef.getFile(file).addOnSuccessListener {
                val bitmap = BitmapFactory.decodeFile(file.absolutePath)
                binding.imageView.setImageBitmap(bitmap)
            }
            .addOnFailureListener(){ e->
                Toast.makeText(this, e.message,  Toast.LENGTH_SHORT).show()
            }
        }
    }

    val startForResult = registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        binding.imageView.setImageURI(uri)
    }

}