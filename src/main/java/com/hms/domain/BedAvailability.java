package com.hms.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "bed_availability")
public class BedAvailability {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "bed_number", unique = true, nullable = false)
    private String bedNumber;

    @Column(name = "ward_name", nullable = false)
    private String wardName; // e.g., "ICU", "General", "Emergency"

    @Column(name = "is_available", nullable = false)
    private Boolean isAvailable = true;

    @Column(name = "bed_type")
    private String bedType; // e.g., "General", "ICU", "VIP"

    @OneToOne
    @JoinColumn(name = "patient_id")
    private Patient patient; // Current patient occupying the bed (if any)

    // Default constructor
    public BedAvailability() {
    }

    // All-args constructor
    public BedAvailability(Long id, String bedNumber, String wardName, Boolean isAvailable,
                          String bedType, Patient patient) {
        this.id = id;
        this.bedNumber = bedNumber;
        this.wardName = wardName;
        this.isAvailable = isAvailable;
        this.bedType = bedType;
        this.patient = patient;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBedNumber() {
        return bedNumber;
    }

    public void setBedNumber(String bedNumber) {
        this.bedNumber = bedNumber;
    }

    public String getWardName() {
        return wardName;
    }

    public void setWardName(String wardName) {
        this.wardName = wardName;
    }

    public Boolean getIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(Boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public String getBedType() {
        return bedType;
    }

    public void setBedType(String bedType) {
        this.bedType = bedType;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}
