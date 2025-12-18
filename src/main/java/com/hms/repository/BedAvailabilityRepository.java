package com.hms.repository;

import com.hms.domain.BedAvailability;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface BedAvailabilityRepository extends JpaRepository<BedAvailability, Long> {

    Optional<BedAvailability> findByBedNumber(String bedNumber);

    List<BedAvailability> findByWardName(String wardName);

    List<BedAvailability> findByIsAvailable(Boolean isAvailable);

    List<BedAvailability> findByWardNameAndIsAvailable(String wardName, Boolean isAvailable);

    Long countByIsAvailable(Boolean isAvailable);

    Long countByWardNameAndIsAvailable(String wardName, Boolean isAvailable);
}
