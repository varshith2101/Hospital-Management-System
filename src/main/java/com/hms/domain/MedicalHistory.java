package com.hms.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "medical_history")
public class MedicalHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "has_diabetes")
    private Boolean hasDiabetes = false;

    @Column(name = "had_heart_attacks")
    private Boolean hadHeartAttacks = false;

    @Column(name = "had_strokes")
    private Boolean hadStrokes = false;

    @Column(name = "had_seizures")
    private Boolean hadSeizures = false;

    @Column(name = "surgeries_info", columnDefinition = "TEXT")
    private String surgeriesInfo;

    @Column(name = "implants_info", columnDefinition = "TEXT")
    private String implantsInfo;

    @Column(name = "medications", columnDefinition = "TEXT")
    private String medications;

    @Column(name = "allergies", columnDefinition = "TEXT")
    private String allergies;

    @Column(name = "medical_restrictions", columnDefinition = "TEXT")
    private String medicalRestrictions;

    @Column(name = "other_issues", columnDefinition = "TEXT")
    private String otherIssues;

    // Default constructor
    public MedicalHistory() {
    }

    // All-args constructor
    public MedicalHistory(Long id, Boolean hasDiabetes, Boolean hadHeartAttacks, Boolean hadStrokes,
                          Boolean hadSeizures, String surgeriesInfo, String implantsInfo, String medications,
                          String allergies, String medicalRestrictions, String otherIssues) {
        this.id = id;
        this.hasDiabetes = hasDiabetes;
        this.hadHeartAttacks = hadHeartAttacks;
        this.hadStrokes = hadStrokes;
        this.hadSeizures = hadSeizures;
        this.surgeriesInfo = surgeriesInfo;
        this.implantsInfo = implantsInfo;
        this.medications = medications;
        this.allergies = allergies;
        this.medicalRestrictions = medicalRestrictions;
        this.otherIssues = otherIssues;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getHasDiabetes() {
        return hasDiabetes;
    }

    public void setHasDiabetes(Boolean hasDiabetes) {
        this.hasDiabetes = hasDiabetes;
    }

    public Boolean getHadHeartAttacks() {
        return hadHeartAttacks;
    }

    public void setHadHeartAttacks(Boolean hadHeartAttacks) {
        this.hadHeartAttacks = hadHeartAttacks;
    }

    public Boolean getHadStrokes() {
        return hadStrokes;
    }

    public void setHadStrokes(Boolean hadStrokes) {
        this.hadStrokes = hadStrokes;
    }

    public Boolean getHadSeizures() {
        return hadSeizures;
    }

    public void setHadSeizures(Boolean hadSeizures) {
        this.hadSeizures = hadSeizures;
    }

    public String getSurgeriesInfo() {
        return surgeriesInfo;
    }

    public void setSurgeriesInfo(String surgeriesInfo) {
        this.surgeriesInfo = surgeriesInfo;
    }

    public String getImplantsInfo() {
        return implantsInfo;
    }

    public void setImplantsInfo(String implantsInfo) {
        this.implantsInfo = implantsInfo;
    }

    public String getMedications() {
        return medications;
    }

    public void setMedications(String medications) {
        this.medications = medications;
    }

    public String getAllergies() {
        return allergies;
    }

    public void setAllergies(String allergies) {
        this.allergies = allergies;
    }

    public String getMedicalRestrictions() {
        return medicalRestrictions;
    }

    public void setMedicalRestrictions(String medicalRestrictions) {
        this.medicalRestrictions = medicalRestrictions;
    }

    public String getOtherIssues() {
        return otherIssues;
    }

    public void setOtherIssues(String otherIssues) {
        this.otherIssues = otherIssues;
    }
}
