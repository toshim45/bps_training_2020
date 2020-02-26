package com.bps.productsurvey.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Date;

public class Survey {
    Integer id;
    String kualitas;
    Integer barangId;
    Integer surveyorId;
    String catatan;
    Date createdAt;
    Date updatedAt;

    public Survey(String kualitas, Integer barangId, Integer surveyorId) {
        this.kualitas = kualitas;
        this.barangId = barangId;
        this.surveyorId = surveyorId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getKualitas() {
        return kualitas;
    }

    public void setKualitas(String kualitas) {
        this.kualitas = kualitas;
    }

    public Integer getBarangId() {
        return barangId;
    }

    public void setBarangId(Integer barangId) {
        this.barangId = barangId;
    }

    public Integer getSurveyorId() {
        return surveyorId;
    }

    public void setSurveyorId(Integer surveyorId) {
        this.surveyorId = surveyorId;
    }

    public String getCatatan() {
        return catatan;
    }

    public void setCatatan(String catatan) {
        this.catatan = catatan;
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

    public String toJsonString() throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(this);
    }
}
