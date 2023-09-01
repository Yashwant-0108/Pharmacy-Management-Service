package com.oneHealth.controller;

import com.oneHealth.entity.Pharmacy;
import com.oneHealth.exception.DatabaseException;
import com.oneHealth.exception.PharmacyNotFoundException;
import com.oneHealth.service.PharmacyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@RestController
@RequestMapping("/pharmacies")
public class PharmacyController {

    private static final Logger LOGGER = Logger.getLogger(PharmacyController.class.getName());

    @Autowired
    private PharmacyService pharmacyService;

    /**
     * Get all pharmacies.
     *
     * @return ResponseEntity<List<Pharmacy>> A response containing a list of pharmacies.
     * @throws DatabaseException If a database error occurs.
     */
    @GetMapping("/getAllPharmacies")
    public ResponseEntity<List<Pharmacy>> getAllPharmacies() throws DatabaseException {
        try {
            LOGGER.info("Fetching all pharmacies");
            List<Pharmacy> pharmacies = pharmacyService.getAllPharmacies();
            return ResponseEntity.ok(pharmacies);
        } catch (DatabaseException e) {
            LOGGER.severe("Error fetching all pharmacies: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Get a pharmacy by ID.
     *
     * @param id The ID of the pharmacy to retrieve.
     * @return ResponseEntity<Pharmacy> A response containing the pharmacy.
     * @throws DatabaseException        If a database error occurs.
     * @throws PharmacyNotFoundException If the pharmacy is not found.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Pharmacy> getPharmacyById(@PathVariable long id) throws DatabaseException, PharmacyNotFoundException {
        try {
            LOGGER.info("Fetching pharmacy by ID: " + id);
            Optional<Pharmacy> pharmacy = pharmacyService.getPharmacyById(id);

            if (pharmacy.isPresent()) {
                LOGGER.info("Pharmacy found for ID: " + id);
                return ResponseEntity.ok(pharmacy.get());
            } else {
                LOGGER.warning("Pharmacy not found for ID: " + id);
                throw new PharmacyNotFoundException("Pharmacy Not Found !!");
            }
        } catch (DatabaseException e) {
            LOGGER.severe("Error fetching pharmacy by ID: " + id + ": " + e.getMessage());
            throw e;
        } catch (PharmacyNotFoundException e) {
            LOGGER.warning("Pharmacy not found for ID: " + id + ": " + e.getMessage());
            throw e;
        }
    }

    /**
     * Add a new pharmacy.
     *
     * @param pharmacy The Pharmacy object to be added.
     * @return ResponseEntity<String> A response indicating the success of the operation.
     * @throws DatabaseException If a database error occurs.
     */
    @PostMapping("/addPharmacies")
    public ResponseEntity<String> saveLab(@RequestBody Pharmacy pharmacy) throws DatabaseException {
        try {
            LOGGER.info("Saving new pharmacy");
            Pharmacy savedPharmacy = pharmacyService.savePharmacy(pharmacy);
            return new ResponseEntity<>("Pharmacy Profile  Added", HttpStatus.CREATED);
        } catch (DatabaseException e) {
            LOGGER.severe("Error saving pharmacy: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Update pharmacy details by ID.
     *
     * @param id             The ID of the pharmacy to update.
     * @param updatedPharmacy The updated Pharmacy object.
     * @return ResponseEntity<String> A response indicating the success of the operation.
     * @throws DatabaseException        If a database error occurs.
     * @throws PharmacyNotFoundException If the pharmacy is not found.
     */
    @PutMapping("/{id}")
    public ResponseEntity<String> updatePharmacy(@PathVariable long id, @RequestBody Pharmacy updatedPharmacy)
            throws DatabaseException, PharmacyNotFoundException {
        try {
            LOGGER.info("Updating pharmacy by ID: " + id);
            pharmacyService.updatePharmacy(updatedPharmacy);
            return ResponseEntity.ok("Pharmacy Details updated successfully.");
        } catch (DatabaseException e) {
            LOGGER.severe("Error updating pharmacy by ID: " + id + ": " + e.getMessage());
            throw e;
        } catch (PharmacyNotFoundException e) {
            LOGGER.warning("Pharmacy not found for ID: " + id + ": " + e.getMessage());
            throw e;
        }
    }

    /**
     * Delete a pharmacy by ID.
     *
     * @param id The ID of the pharmacy to delete.
     * @return ResponseEntity<String> A response indicating the success of the operation.
     * @throws DatabaseException        If a database error occurs.
     * @throws PharmacyNotFoundException If the pharmacy is not found.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteLabById(@PathVariable long id) throws DatabaseException, PharmacyNotFoundException {
        try {
            LOGGER.info("Deleting pharmacy by ID: " + id);
            pharmacyService.deleteLabById(id);
            return ResponseEntity.ok("Pharmacy deleted successfully.");
        } catch (DatabaseException e) {
            LOGGER.severe("Error deleting pharmacy by ID: " + id + ": " + e.getMessage());
            throw e;
        } catch (PharmacyNotFoundException e) {
            LOGGER.warning("Pharmacy not found for ID: " + id + ": " + e.getMessage());
            throw e;
        }
    }

    /**
     * Get pharmacies by city.
     *
     * @param city The city to filter by.
     * @return ResponseEntity<List<Pharmacy>> A response containing a list of pharmacies in the specified city.
     * @throws DatabaseException If a database error occurs.
     */
    @GetMapping("/pharmacies")
    public ResponseEntity<List<Pharmacy>> getPharmacyByCity(@RequestParam("city") String city) throws DatabaseException {
        try {
            LOGGER.info("Fetching pharmacies by city: " + city);
            List<Pharmacy> pharmacies = pharmacyService.getPharmacyByCity(city);
            return ResponseEntity.ok(pharmacies);
        } catch (DatabaseException e) {
            LOGGER.severe("Error fetching pharmacies by city: " + city + ": " + e.getMessage());
            throw e;
        }
    }
}
