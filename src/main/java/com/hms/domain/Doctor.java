package com.hms.domain;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "doctors")
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer age;

    @Column(nullable = false)
    private String specialization;

    private String qualification;

    @Column(name = "shift_type")
    private String shiftType;

    @Column(name = "opd_days")
    private String opdDays;

    @Column(name = "date_of_joining")
    private String dateOfJoining;

    private Double salary;

    @Column(name = "is_available")
    private Boolean isAvailable = true;

    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL)
    private List<Appointment> appointments;

    // Constructors
    public Doctor() {}

    public Doctor(Long id, String name, Integer age, String specialization, String qualification,
                  String shiftType, String opdDays, String dateOfJoining, Double salary,
                  Boolean isAvailable, List<Appointment> appointments) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.specialization = specialization;
        this.qualification = qualification;
        this.shiftType = shiftType;
        this.opdDays = opdDays;
        this.dateOfJoining = dateOfJoining;
        this.salary = salary;
        this.isAvailable = isAvailable;
        this.appointments = appointments;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Integer getAge() { return age; }
    public void setAge(Integer age) { this.age = age; }

    public String getSpecialization() { return specialization; }
    public void setSpecialization(String specialization) { this.specialization = specialization; }

    public String getQualification() { return qualification; }
    public void setQualification(String qualification) { this.qualification = qualification; }

    public String getShiftType() { return shiftType; }
    public void setShiftType(String shiftType) { this.shiftType = shiftType; }

    public String getOpdDays() { return opdDays; }
    public void setOpdDays(String opdDays) { this.opdDays = opdDays; }

    public String getDateOfJoining() { return dateOfJoining; }
    public void setDateOfJoining(String dateOfJoining) { this.dateOfJoining = dateOfJoining; }

    public Double getSalary() { return salary; }
    public void setSalary(Double salary) { this.salary = salary; }

    public Boolean getIsAvailable() { return isAvailable; }
    public void setIsAvailable(Boolean isAvailable) { this.isAvailable = isAvailable; }

    public List<Appointment> getAppointments() { return appointments; }
    public void setAppointments(List<Appointment> appointments) { this.appointments = appointments; }
}
