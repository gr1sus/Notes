package com.expensive.notes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton

class EditActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.edit_activity)
        val imageLayout = findViewById<ConstraintLayout>(R.id.myImageLayout)
        val buttPh = findViewById<FloatingActionButton>(R.id.floatButtAddPh)
        val buttDel = findViewById<ImageButton>(R.id.buttDel)

        buttPh.setOnClickListener{
            imageLayout.visibility = View.VISIBLE
            buttPh.visibility = View.GONE
        }

        buttDel.setOnClickListener{
            imageLayout.visibility = View.GONE
            buttPh.visibility = View.VISIBLE
        }


    }
}