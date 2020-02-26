package com.bps.productsurvey.model;

import java.util.Date;

public class Barang {
    private Integer id;
    protected String nama;
    private Date createdAt;
    private Date updatedAt;

    public Barang(String nama) {
        this.nama = nama;
    }

    public Barang(Integer id, String nama, Date createdAt, Date updatedAt) {
        this.id = id;
        this.nama = nama;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }
    
    public String getNama() {
        return nama;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
