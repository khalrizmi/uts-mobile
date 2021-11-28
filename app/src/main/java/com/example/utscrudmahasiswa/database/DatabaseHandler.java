package com.example.utscrudmahasiswa.database;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class DatabaseHandler extends SQLiteOpenHelper {
    // static variable
    private static final int DATABASE_VERSION = 1;

    // Database name
    private static final String DATABASE_NAME = "curd_mahasiswa";

    public static final String TABLE_JURUSAN = "jurusans";
    public static final String TABLE_MAHASISWA = "mahasiswas";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql_table_jurusan = "CREATE TABLE " + TABLE_JURUSAN + "(kode TEXT, nama TEXT, jenjang TEXT)";
        String sql_table_mahasiswa = "CREATE TABLE " + TABLE_MAHASISWA + "(npm TEXT, kodeJurusan TEXT, nama TEXT, angkatan TEXT)";

        sqLiteDatabase.execSQL(sql_table_jurusan);
        sqLiteDatabase.execSQL(sql_table_mahasiswa);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
