package com.expensive.notes

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import com.expensive.notes.db.MyDBManager
import com.expensive.notes.db.MyIntentConstance
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
        val imMainImage = findViewById<ImageView>(R.id.ImageUser)


        fun getMyIntents() {

            val i = intent
            if (i != null) {
                if (i.getStringExtra(MyIntentConstance.I_TITLE_KEY) != null) {
                    buttPh.visibility = View.GONE


                    edTitle.setText(i.getStringExtra(MyIntentConstance.I_TITLE_KEY))
                    edDesc.setText(i.getStringExtra(MyIntentConstance.I_DESC_KEY))
                    if (i.getStringExtra(MyIntentConstance.I_URI_KEY) != "empty") {
                        imageLayout.visibility = View.VISIBLE
                        imMainImage.setImageURI(Uri.parse(i.getStringExtra(MyIntentConstance.I_URI_KEY)))
                        buttDel.visibility = View.GONE
                        buttEdit.visibility = View.GONE
                    }
                }
            }

        }
        getMyIntents()

        buttPh.setOnClickListener {
            imageLayout.visibility = View.VISIBLE
            buttPh.visibility = View.GONE
        }

        buttDel.setOnClickListener {
            imageLayout.visibility = View.GONE
            buttPh.visibility = View.VISIBLE
        }

        buttEdit.setOnClickListener {
            val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
            intent.type = "image/*"

            startActivityForResult(intent, imageRequestCode)
        }

        buttSave.setOnClickListener {
            val myTitle = edTitle.text.toString()
            val myDesc = edDesc.text.toString()
            if (myTitle != "" && myDesc != "") {
                myDbManager.insertToDb(myTitle, myDesc, tempImageUri)
                finish()
            }
        }


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val imMainImage = findViewById<ImageView>(R.id.ImageUser)
        if (resultCode == RESULT_OK && requestCode == imageRequestCode) {
            imMainImage.setImageURI(data?.data)
            tempImageUri = data?.data.toString()
            contentResolver.takePersistableUriPermission(data?.data!!, Intent.FLAG_GRANT_READ_URI_PERMISSION)
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