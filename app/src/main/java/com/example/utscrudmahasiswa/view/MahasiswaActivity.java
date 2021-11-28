package com.example.utscrudmahasiswa.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.utscrudmahasiswa.R;
import com.example.utscrudmahasiswa.database.DatabaseHandler;
import com.example.utscrudmahasiswa.model.Jurusan;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MahasiswaActivity extends AppCompatActivity {

    private static final String BTN_SIMPAN = "Insert";
    private static final String BTN_UPDATE = "Edit";

    DatabaseHandler databaseHandler;
    SQLiteDatabase sqLiteDatabase;

    Spinner spnrJurusan;
    EditText etNpm, etNama, etAngkatan;
    Button btnSimpan, btnDelete, btnSearch;

    ArrayList<Jurusan> jurusans = new ArrayList<>();

    private Timer timer = new Timer();
    private final long DELAY = 1000L;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mahasiswa);

        initDatabase();
        initView();
        initSpinner();

        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Jurusan jurusan = (Jurusan) spnrJurusan.getSelectedItem();

                String npm = etNpm.getText().toString();
                String kodeJurusan = jurusan.getKode();
                String nama = etNama.getText().toString();
                String angkatan = etAngkatan.getText().toString();

                if (btnSimpan.getText().toString().equals(BTN_SIMPAN)) {
                    saveMahasiswa(npm, kodeJurusan, nama, angkatan);
                } else if (btnSimpan.getText().toString().equals(BTN_UPDATE)) {
                    updateMahasiswa(npm, kodeJurusan, nama, angkatan);
                }
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String npm = etNpm.getText().toString();

                deleteMahasiswa(npm);
            }
        });
    }

    void initDatabase() {
        databaseHandler = new DatabaseHandler(this);
        sqLiteDatabase = databaseHandler.getWritableDatabase();
    }

    void initView() {
        spnrJurusan = findViewById(R.id.spnr_jurusan);
        etNpm = findViewById(R.id.et_npm);
        etNama = findViewById(R.id.et_nama);
        etAngkatan = findViewById(R.id.et_angkatan);
        btnSimpan = findViewById(R.id.btn_simpan);
        btnDelete = findViewById(R.id.btn_delete);
        btnSearch = findViewById(R.id.btn_search);

        btnDelete.setVisibility(View.GONE);

        etNpm.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                timer.cancel();
                timer = new Timer();
                timer.schedule(new MyTimerTask(), DELAY);
            }
        });
    }

    private void initSpinner() {
        jurusans = getJurusans();

        ArrayAdapter<Jurusan> adapter = new ArrayAdapter<Jurusan>(this, android.R.layout.simple_spinner_dropdown_item, jurusans);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spnrJurusan.setAdapter(adapter);

    }

    private ArrayList<Jurusan> getJurusans() {
        ArrayList<Jurusan> jurusans = new ArrayList<Jurusan>();

        String sql = "SELECT * FROM " + DatabaseHandler.TABLE_JURUSAN;

        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            do {
                Jurusan jurusan = new Jurusan();
                jurusan.setKode(cursor.getString(0));
                jurusan.setNama(cursor.getString(1));
                jurusan.setJenjang(cursor.getString(2));

                jurusans.add(jurusan);
            } while (cursor.moveToNext());
        }

        Toast.makeText(this, "berhasil mengambil data " + jurusans.size(), Toast.LENGTH_SHORT).show();

        return jurusans;
    }

    private void saveMahasiswa(String npm, String kodeJurusan, String nama, String angkatan) {
        initDatabase();

        ContentValues values = new ContentValues();

        values.put("npm", npm);
        values.put("kodeJurusan", kodeJurusan);
        values.put("nama", nama);
        values.put("angkatan", angkatan);

        sqLiteDatabase.insert(DatabaseHandler.TABLE_MAHASISWA, null, values);

        sqLiteDatabase.close();

        Toast.makeText(this, "Mahasiswa berhasil disimpan", Toast.LENGTH_SHORT).show();

        finish();
    }

    private void updateMahasiswa(String npm, String kodeJurusan, String nama, String angkatan) {
        initDatabase();

        ContentValues values = new ContentValues();

        values.put("kodeJurusan", kodeJurusan);
        values.put("nama", nama);
        values.put("angkatan", angkatan);

        sqLiteDatabase.update(DatabaseHandler.TABLE_MAHASISWA, values, "npm=?", new String[]{npm});

        sqLiteDatabase.close();

        Toast.makeText(this, "Mahasiswa berhasil diupdate", Toast.LENGTH_SHORT).show();

        finish();
    }

    private void deleteMahasiswa(String npm) {
        initDatabase();

        sqLiteDatabase.delete(DatabaseHandler.TABLE_MAHASISWA, "npm=?", new String[]{npm});

        sqLiteDatabase.close();

        btnSimpan.setText(BTN_SIMPAN);
        btnDelete.setVisibility(View.GONE);

        etNpm.setText("");
        etNama.setText("");
        etAngkatan.setText("");

        Toast.makeText(this, "Mahasiswa berhasil dihapus", Toast.LENGTH_SHORT).show();
    }

    private class MyTimerTask extends TimerTask {

        @Override
        public void run() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Log.e("TIMER", "start timer");

                    String npm = etNpm.getText().toString();

                    String sql = "SELECT * FROM " + DatabaseHandler.TABLE_MAHASISWA +
                            " WHERE npm = ?";

                    Cursor cursor = sqLiteDatabase.rawQuery(sql, new String[]{npm});

                    if (cursor.getCount() > 0) {
                        cursor.moveToFirst();

                        btnSimpan.setText(BTN_UPDATE);
                        btnDelete.setVisibility(View.VISIBLE);

                        etNama.setText(cursor.getString(2));
                        etAngkatan.setText(cursor.getString(3));

                        Log.e("TIMER", "ditemukan");
                    } else {
                        btnSimpan.setText(BTN_SIMPAN);
                        btnDelete.setVisibility(View.GONE);

                        etNama.setText("");
                        etAngkatan.setText("");

                        Log.e("TIMER", "tidak ditemukan");
                    }
                }
            });
        }
    }
}