package com.example.utscrudmahasiswa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.utscrudmahasiswa.view.JurusanActivity;
import com.example.utscrudmahasiswa.view.MahasiswaActivity;

public class MainActivity extends AppCompatActivity {

    Button btnJurusan, btnMahasiswa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnJurusan = (Button) findViewById(R.id.btn_jurusan);
        btnMahasiswa = (Button) findViewById(R.id.btn_mahasiswa);

        btnJurusan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), JurusanActivity.class);
                startActivity(intent);
            }
        });

        btnMahasiswa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), MahasiswaActivity.class);
                startActivity(intent);
            }
        });
    }
}