package com.hms.controller;

import com.hms.domain.BedAvailability;
import com.hms.service.BedAvailabilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/beds")
public class BedAvailabilityController {

    @Autowired
    private BedAvailabilityService bedAvailabilityService;

    @PostMapping
    public ResponseEntity<BedAvailability> createBed(@RequestBody BedAvailability bed) {
        BedAvailability created = bedAvailabilityService.createBed(bed);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<BedAvailability>> getAllBeds() {
        return ResponseEntity.ok(bedAvailabilityService.getAllBeds());
    }

    @GetMapping("/available")
    public ResponseEntity<List<BedAvailability>> getAvailableBeds() {
        return ResponseEntity.ok(bedAvailabilityService.getAvailableBeds());
    }

    @GetMapping("/available/ward/{wardName}")
    public ResponseEntity<List<BedAvailability>> getAvailableBedsByWard(@PathVariable String wardName) {
        return ResponseEntity.ok(bedAvailabilityService.getAvailableBedsByWard(wardName));
    }

    // Real-time bed availability statistics
    @GetMapping("/stats")
    public ResponseEntity<Map<String, Object>> getBedStats() {
        return ResponseEntity.ok(bedAvailabilityService.getBedAvailabilityStats());
    }

    // Real-time bed availability by ward
    @GetMapping("/stats/ward/{wardName}")
    public ResponseEntity<Map<String, Object>> getBedStatsByWard(@PathVariable String wardName) {
        return ResponseEntity.ok(bedAvailabilityService.getBedAvailabilityByWard(wardName));
    }

    // Allocate bed to patient (real-time allocation)
    @PostMapping("/allocate")
    public ResponseEntity<BedAvailability> allocateBed(
            @RequestParam String bedNumber,
            @RequestParam Long patientId) {
        try {
            BedAvailability allocated = bedAvailabilityService.allocateBed(bedNumber, patientId);
            return ResponseEntity.ok(allocated);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // Release bed (real-time release)
    @PostMapping("/release")
    public ResponseEntity<BedAvailability> releaseBed(@RequestParam String bedNumber) {
        try {
            BedAvailability released = bedAvailabilityService.releaseBed(bedNumber);
            return ResponseEntity.ok(released);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
