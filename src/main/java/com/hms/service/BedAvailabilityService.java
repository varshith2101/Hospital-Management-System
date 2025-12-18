package com.hms.service;

import com.hms.domain.BedAvailability;
import com.hms.domain.Patient;
import com.hms.repository.BedAvailabilityRepository;
import com.hms.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class BedAvailabilityService {

    @Autowired
    private BedAvailabilityRepository bedAvailabilityRepository;

    @Autowired
    private PatientRepository patientRepository;

    public BedAvailability createBed(BedAvailability bed) {
        return bedAvailabilityRepository.save(bed);
    }

    public List<BedAvailability> getAllBeds() {
        return bedAvailabilityRepository.findAll();
    }

    public List<BedAvailability> getAvailableBeds() {
        return bedAvailabilityRepository.findByIsAvailable(true);
    }

    public List<BedAvailability> getAvailableBedsByWard(String wardName) {
        return bedAvailabilityRepository.findByWardNameAndIsAvailable(wardName, true);
    }

    // Real-time bed availability stats
    public Map<String, Object> getBedAvailabilityStats() {
        Map<String, Object> stats = new HashMap<>();
        stats.put("totalBeds", bedAvailabilityRepository.count());
        stats.put("availableBeds", bedAvailabilityRepository.countByIsAvailable(true));
        stats.put("occupiedBeds", bedAvailabilityRepository.countByIsAvailable(false));
        return stats;
    }

    // Real-time bed availability by ward
    public Map<String, Object> getBedAvailabilityByWard(String wardName) {
        Map<String, Object> stats = new HashMap<>();
        Long total = (long) bedAvailabilityRepository.findByWardName(wardName).size();
        Long available = bedAvailabilityRepository.countByWardNameAndIsAvailable(wardName, true);
        stats.put("wardName", wardName);
        stats.put("totalBeds", total);
        stats.put("availableBeds", available);
        stats.put("occupiedBeds", total - available);
        return stats;
    }

    // Allocate bed to patient
    public BedAvailability allocateBed(String bedNumber, Long patientId) {
        BedAvailability bed = bedAvailabilityRepository.findByBedNumber(bedNumber)
                .orElseThrow(() -> new RuntimeException("Bed not found"));

        if (!bed.getIsAvailable()) {
            throw new RuntimeException("Bed is already occupied");
        }

        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        bed.setIsAvailable(false);
        bed.setPatient(patient);

        // Update patient status
        patient.setIsAdmitted(true);
        patient.setBedNumber(bedNumber);
        patientRepository.save(patient);

        return bedAvailabilityRepository.save(bed);
    }

    // Release bed
    public BedAvailability releaseBed(String bedNumber) {
        BedAvailability bed = bedAvailabilityRepository.findByBedNumber(bedNumber)
                .orElseThrow(() -> new RuntimeException("Bed not found"));

        if (bed.getPatient() != null) {
            Patient patient = bed.getPatient();
            patient.setIsAdmitted(false);
            patient.setBedNumber(null);
            patientRepository.save(patient);
        }

        bed.setIsAvailable(true);
        bed.setPatient(null);

        return bedAvailabilityRepository.save(bed);
    }
}
