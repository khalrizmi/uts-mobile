package com.example.utscrudmahasiswa.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.utscrudmahasiswa.R;
import com.example.utscrudmahasiswa.database.DatabaseHandler;

public class JurusanActivity extends AppCompatActivity {

    DatabaseHandler databaseHandler;
    SQLiteDatabase sqLiteDatabase;

    EditText etKode, etNama, etJenjang;
    Button btnSimpan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jurusan);

        initDatabase();
        initView();

        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String kode = etKode.getText().toString();
                String nama = etNama.getText().toString();
                String jenjang = etJenjang.getText().toString();

                saveJurusan(kode, nama, jenjang);
            }
        });
    }

    void initDatabase() {
        databaseHandler = new DatabaseHandler(this);
        sqLiteDatabase = databaseHandler.getWritableDatabase();
    }

    void initView() {
        etKode = findViewById(R.id.et_kode);
        etNama = findViewById(R.id.et_nama);
        etJenjang = findViewById(R.id.et_jenjang);

        btnSimpan = findViewById(R.id.btn_save);
    }

    void saveJurusan(String kode, String nama, String jenjang) {
        ContentValues values = new ContentValues();

        values.put("kode", kode);
        values.put("nama", nama);
        values.put("jenjang", jenjang);

        sqLiteDatabase.insert(DatabaseHandler.TABLE_JURUSAN, null, values);

        sqLiteDatabase.close();

        Toast.makeText(this, "Jurusan berhasil disimpan", Toast.LENGTH_SHORT).show();

        finish();
    }
}