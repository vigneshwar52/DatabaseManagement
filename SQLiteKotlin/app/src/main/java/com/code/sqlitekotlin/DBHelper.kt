package com.code.sqlitekotlin

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(private val context: Context):SQLiteOpenHelper(context, DATABASE_NAME,null, DATABASE_VERSION) {

    companion object{
        private val DATABASE_NAME = "SQLITE.db"
        private val DATABASE_VERSION = 1;
        private val TABLE_NAME = "StudentTable"
        private val COLUMN_ID = "id"
        val COLUMN_NAME = "name"
        val COLUMN_AGE = "age"
    }
    override fun onCreate(db: SQLiteDatabase?) {
        //CREATE TABLE StudentTable (id INTEGER PRIMARY KEY,name TEXT,age INTEGER)
        val query = ("CREATE TABLE "+ TABLE_NAME +"("+ COLUMN_ID + " INTEGER PRIMARY KEY,"+ COLUMN_NAME +" TEXT,"+ COLUMN_AGE+" TEXT "+")")
        db?.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun addContent(name:String,age:String){
        val values = ContentValues();

        values.put(COLUMN_NAME,name)
        values.put(COLUMN_AGE,age)

        val db = this.writableDatabase

        db.insert(TABLE_NAME,null,values)

        db.close()
    }

    fun getALL():Cursor? {
        val db = this.writableDatabase
        return db.rawQuery("SELECT * FROM "+ TABLE_NAME,null)
    }

    fun getName():Cursor? {
        val db = this.writableDatabase
        return db.rawQuery("SELECT "+ COLUMN_NAME+" FROM "+ TABLE_NAME,null)
    }
    fun getAge():Cursor? {
        val db = this.writableDatabase
        return db.rawQuery("SELECT "+ COLUMN_AGE+" FROM "+ TABLE_NAME,null)
    }
    fun getContent(column:String):Cursor? {
        val db = this.writableDatabase
        return db.rawQuery("SELECT "+ column +" FROM "+ TABLE_NAME,null)
    }
}