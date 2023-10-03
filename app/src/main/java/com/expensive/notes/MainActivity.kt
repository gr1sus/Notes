package com.expensive.notes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.expensive.notes.db.MyDBManager
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    val myDbManager = MyDBManager(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var buttonNew = findViewById<FloatingActionButton>(R.id.fbNew)


        buttonNew.setOnClickListener{

            var i = Intent(this,EditActivity::class.java)
            startActivity(i)

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