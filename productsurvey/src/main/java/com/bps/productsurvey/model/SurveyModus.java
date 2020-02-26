package com.bps.productsurvey.model;

import java.io.Serializable;

public class SurveyModus implements Serializable {
    private String barang;
    private String kualitas;
    private int jumlah;

    public SurveyModus(String barang, String kualitas, int jumlah) {
        this.barang = barang;
        this.kualitas = kualitas;
        this.jumlah = jumlah;
    }

    public String getBarang() {
        return barang;
    }

    public void setBarang(String barang) {
        this.barang = barang;
    }

    public String getKualitas() {
        return kualitas;
    }

    public void setKualitas(String kualitas) {
        this.kualitas = kualitas;
    }

    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }
}
