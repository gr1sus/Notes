package com.expensive.notes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import com.expensive.notes.db.MyDBManager
import com.google.android.material.floatingactionbutton.FloatingActionButton

class EditActivity : AppCompatActivity() {

    val myDbManager = MyDBManager(this)
    val imageRequestCode = 10
    var tempImageUri = "empty"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.edit_activity)
        val imageLayout = findViewById<ConstraintLayout>(R.id.myImageLayout)
        val buttPh = findViewById<FloatingActionButton>(R.id.floatButtAddPh)
        val buttDel = findViewById<ImageButton>(R.id.buttDel)
        val buttEdit = findViewById<ImageButton>(R.id.buttEdit)
        val buttSave = findViewById<FloatingActionButton>(R.id.FbSave)
        val edTitle = findViewById<EditText>(R.id.edTitle)
        val edDesc = findViewById<EditText>(R.id.edDisck)

        buttPh.setOnClickListener{
            imageLayout.visibility = View.VISIBLE
            buttPh.visibility = View.GONE
        }

        buttDel.setOnClickListener{
            imageLayout.visibility = View.GONE
            buttPh.visibility = View.VISIBLE
        }

        buttEdit.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, imageRequestCode)
        }

        buttSave.setOnClickListener {
            val myTitle = edTitle.text.toString()
            val myDesc = edDesc.text.toString()
            if (myTitle != "" && myDesc!= ""){
                myDbManager.insertToDb(myTitle,myDesc,tempImageUri)
            }
        }


    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val imMainImage = findViewById<ImageView>(R.id.ImageUser)
        if (resultCode == RESULT_OK && requestCode == imageRequestCode){
            imMainImage.setImageURI(data?.data)
            tempImageUri = data?.data.toString()
        }
    }
    override fun onResume() {
        super.onResume()

        myDbManager.openDb()

    }

    override fun onDestroy() {
        super.onDestroy()
        myDbManager.closeDb()
    }
}