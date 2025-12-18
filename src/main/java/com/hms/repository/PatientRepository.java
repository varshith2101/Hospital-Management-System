package com.hms.repository;

import com.hms.domain.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

    Optional<Patient> findByName(String name);

    Optional<Patient> findByPhoneNumber(String phoneNumber);

    List<Patient> findByIsAdmitted(Boolean isAdmitted);

    List<Patient> findByGender(String gender);
}
