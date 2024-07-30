package com.example.sozlukuygulamasi

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

// veritanbanını temsileden sınıf
class VeritabaniYardimci(private val context : Context) : SQLiteOpenHelper(context,"sozluk.sqlite",null,1) {

    // TABLOLARI OLUŞTURDUĞUMUZ METHOD
    override fun onCreate(db: SQLiteDatabase?) {

        db?.execSQL("CREATE TABLE  IF NOT EXISTS `kelimeler` (\n" +
                "\t`kelime_id`\tINTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "\t`ingilizce`\tTEXT,\n" +
                "\t`turkce`\tTEXT\n" +
                ")")
    }

    // VERİTABANNDA SORUN OLUŞURSA
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

            db?.execSQL("DROP TABLE IF EXISTS kelimeler ")
            onCreate(db)
    }


}