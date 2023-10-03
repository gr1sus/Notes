package com.expensive.notes.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase

class MyDBManager ( context:Context) {
    val myDBHelper = MyDBHelper(context)
    var db:SQLiteDatabase? = null

    fun openDb(){
        db = myDBHelper.writableDatabase
    }
    fun insertToDb( title: String, content: String){
        val values = ContentValues().apply {
            put(MyDBNameClass.COLUMN_NAME_TITLE, title)
            put(MyDBNameClass.COLUMN_NAME_CONTENT, content)
        }
        db?.insert(MyDBNameClass.TABLE_NAME,null,values)
    }

    fun readDbData():ArrayList<String>{
        val dataList = ArrayList<String>()
        val cursor = db?.query(MyDBNameClass.TABLE_NAME,null,null,null,null,null,null)

            while(cursor?.moveToNext()!!){
                val dataText = cursor.getString(cursor.getColumnIndexOrThrow(MyDBNameClass.COLUMN_NAME_TITLE))
                dataList.add(dataText.toString())
            }
        cursor.close()
        return dataList
    }

    fun closeDb(){
        myDBHelper.close()
    }

}