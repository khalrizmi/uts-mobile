package com.example.utscrudmahasiswa.view;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.utscrudmahasiswa.R;
import com.example.utscrudmahasiswa.database.DatabaseHandler;
import com.example.utscrudmahasiswa.model.Mahasiswa;

public class SearchMahasiswaActivity extends AppCompatActivity {

    DatabaseHandler databaseHandler;
    SQLiteDatabase sqLiteDatabase;

    EditText etSearch, etList;
    Button btnSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_mahasiswa);

        initDatabase();

        etSearch = findViewById(R.id.et_search);
        etList = findViewById(R.id.et_list);
        btnSearch = findViewById(R.id.btn_search);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value = etSearch.getText().toString();
                search(value);
            }
        });
    }

    void initDatabase() {
        databaseHandler = new DatabaseHandler(this);
        sqLiteDatabase = databaseHandler.getWritableDatabase();
    }

    void search(String value) {
        if (value.isEmpty()) {
            etList.setText("");
        } else {
            String result = "";

            initDatabase();

            String sql = "SELECT * FROM " + DatabaseHandler.TABLE_MAHASISWA + " WHERE " +
                    "nama LIKE '%" + value + "%' OR " +
                    "npm LIKE '%" + value + "%'";

            Cursor cursor = sqLiteDatabase.rawQuery(sql, null);

            if (cursor.moveToFirst()) {
                do {
                    String nama = cursor.getString(2);

                    result = result + nama + ";";
                } while (cursor.moveToNext());
            }

            sqLiteDatabase.close();

            etList.setText(result);
        }
    }
}