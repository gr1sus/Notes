package com.expensive.notes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.expensive.notes.db.Adapter
import com.expensive.notes.db.MyDBManager
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    val myDbManager = MyDBManager(this)
    val myAdapter = Adapter(ArrayList(), this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var buttonNew = findViewById<FloatingActionButton>(R.id.fbNew)

        init()


        buttonNew.setOnClickListener{

            var i = Intent(this,EditActivity::class.java)
            startActivity(i)

        }

    }

    override fun onResume() {
        super.onResume()
        myDbManager.openDb()
        fillAdapter()
    }

    override fun onDestroy() {
        super.onDestroy()
        myDbManager.closeDb()
    }
    fun init(){
        var rcView = findViewById<RecyclerView>(R.id.rcView)
        rcView.layoutManager = LinearLayoutManager(this)
        rcView.adapter = myAdapter
    }

    fun fillAdapter(){
        val tvEmpty = findViewById<TextView>(R.id.tvempty)
        val list = myDbManager.readDbData()
        myAdapter.updateAdapter(list)
        if(list.size>0){
            tvEmpty.visibility = View.GONE
        }
        else {
            tvEmpty.visibility = View.VISIBLE
        }
    }
}