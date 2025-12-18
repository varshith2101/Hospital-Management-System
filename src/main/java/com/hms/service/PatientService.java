package com.hms.service;

import com.hms.domain.Patient;
import com.hms.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    public Patient createPatient(Patient patient) {
        return patientRepository.save(patient);
    }

    public Optional<Patient> getPatientById(Long id) {
        return patientRepository.findById(id);
    }

    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    public List<Patient> getAdmittedPatients() {
        return patientRepository.findByIsAdmitted(true);
    }

    public Patient updatePatient(Long id, Patient patientDetails) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        patient.setName(patientDetails.getName());
        patient.setAge(patientDetails.getAge());
        patient.setHeight(patientDetails.getHeight());
        patient.setWeight(patientDetails.getWeight());
        patient.setGender(patientDetails.getGender());
        patient.setPhoneNumber(patientDetails.getPhoneNumber());
        patient.setIsAdmitted(patientDetails.getIsAdmitted());
        patient.setBedNumber(patientDetails.getBedNumber());

        return patientRepository.save(patient);
    }

    public void deletePatient(Long id) {
        patientRepository.deleteById(id);
    }

    // Admit patient (real-time bed allocation)
    public Patient admitPatient(Long id, String bedNumber) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient not found"));
        patient.setIsAdmitted(true);
        patient.setBedNumber(bedNumber);
        return patientRepository.save(patient);
    }

    // Discharge patient
    public Patient dischargePatient(Long id) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient not found"));
        patient.setIsAdmitted(false);
        patient.setBedNumber(null);
        return patientRepository.save(patient);
    }
}
