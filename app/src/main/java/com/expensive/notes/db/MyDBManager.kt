package com.expensive.notes.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase

class MyDBManager(context: Context) {
    val myDBHelper = MyDBHelper(context)
    var db: SQLiteDatabase? = null

    fun openDb() {
        db = myDBHelper.writableDatabase
    }

    fun insertToDb(title: String, content: String, uri: String) {
        val values = ContentValues().apply {
            put(MyDBNameClass.COLUMN_NAME_TITLE, title)
            put(MyDBNameClass.COLUMN_NAME_CONTENT, content)
            put(MyDBNameClass.COLUMN_NAME_IMAGE_URI, uri)
        }
        db?.insert(MyDBNameClass.TABLE_NAME, null, values)
    }

    fun readDbData(): ArrayList<ListItem> {
        val dataList = ArrayList<ListItem>()
        val cursor = db?.query(MyDBNameClass.TABLE_NAME, null, null, null, null, null, null)

        while (cursor?.moveToNext()!!) {
            val dataTitle =
                cursor.getString(cursor.getColumnIndexOrThrow(MyDBNameClass.COLUMN_NAME_TITLE))
            val dataContent =
                cursor.getString(cursor.getColumnIndexOrThrow(MyDBNameClass.COLUMN_NAME_CONTENT))
            val dataUri =
                cursor.getString(cursor.getColumnIndexOrThrow(MyDBNameClass.COLUMN_NAME_IMAGE_URI))
            val item = ListItem()
            item.title = dataTitle
            item.desc = dataContent
            item.uri = dataUri
            dataList.add(item)
        }
        cursor.close()
        return dataList
    }

    fun closeDb() {
        myDBHelper.close()
    }

}