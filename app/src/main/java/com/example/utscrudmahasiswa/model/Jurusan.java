package com.example.utscrudmahasiswa.model;

public class Jurusan {
    String kode;
    String nama;
    String jenjang;

    public Jurusan() {
    }

    public Jurusan(String kode, String nama, String jenjang) {
        this.kode = kode;
        this.nama = nama;
        this.jenjang = jenjang;
    }

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getJenjang() {
        return jenjang;
    }

    public void setJenjang(String jenjang) {
        this.jenjang = jenjang;
    }

    @Override
    public String toString() {
        return nama;
    }
}
