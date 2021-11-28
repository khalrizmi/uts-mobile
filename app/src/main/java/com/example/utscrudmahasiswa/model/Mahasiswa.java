package com.example.utscrudmahasiswa.model;

public class Mahasiswa {
    String npm;
    String kodeJurusan;
    String nama;
    String angkatan;

    public Mahasiswa(String npm, String kodeJurusan, String nama, String angkatan) {
        this.npm = npm;
        this.kodeJurusan = kodeJurusan;
        this.nama = nama;
        this.angkatan = angkatan;
    }

    public String getNpm() {
        return npm;
    }

    public void setNpm(String npm) {
        this.npm = npm;
    }

    public String getKodeJurusan() {
        return kodeJurusan;
    }

    public void setKodeJurusan(String kodeJurusan) {
        this.kodeJurusan = kodeJurusan;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAngkatan() {
        return angkatan;
    }

    public void setAngkatan(String angkatan) {
        this.angkatan = angkatan;
    }
}
