package com.hms.repository;

import com.hms.domain.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    Optional<Doctor> findByName(String name);

    List<Doctor> findBySpecialization(String specialization);

    List<Doctor> findByIsAvailable(Boolean isAvailable);

    List<Doctor> findBySpecializationAndIsAvailable(String specialization, Boolean isAvailable);
}
