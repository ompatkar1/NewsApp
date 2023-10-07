package com.androiddevs.mvvmnewsapp.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(private val context: Context):
    SQLiteOpenHelper(context, DATABASE_NAME,null, DATABASE_VERSION){

    companion object {
        private const val DATABASE_NAME = "UserDatabase.db"
        private const val  DATABASE_VERSION= 1
        private const val TABLE_NAME = "data"
        private const val COLUMN_ID = "id"
        private const val COLUMN_USERNAME = "username"
        private const val COLUMN_PASSWORD = "password"

    }
    override fun onCreate(db: SQLiteDatabase?) {
        val createTableQuery = ("Create Table $TABLE_NAME("+
                "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "$COLUMN_USERNAME TEXT ,"+
                "$COLUMN_PASSWORD TEXT)")
        db?.execSQL(createTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val dropTableQuery = "DROP TABLE IF EXISTS $TABLE_NAME"
        db?.execSQL(dropTableQuery)
        onCreate(db)
    }

    fun insertUser(username:String,password:String):Long{
        val values = ContentValues().apply {
            put(COLUMN_USERNAME,username)
            put(COLUMN_PASSWORD,password)
        }
        val  db = writableDatabase
        return db.insert(TABLE_NAME,null,values)
    }
    fun readUser(username: String,password: String):Boolean{
        val db = readableDatabase
        val selection="$COLUMN_USERNAME = ? AND $COLUMN_PASSWORD = ?"
        val selectionArgs = arrayOf(username,password)
        val cursor =db.query(TABLE_NAME,null,selection,selectionArgs,null,null,null)

        val userExists = cursor.count > 0
        cursor.close()
        return userExists
    }
    fun doesUsernameExist(username: String): Boolean {
        val db = this.readableDatabase
        val query = "SELECT * FROM users WHERE username = ?"
        val cursor = db.rawQuery(query, arrayOf(username))

        val usernameExists = cursor.count > 0
        cursor.close()
        return usernameExists
    }


}