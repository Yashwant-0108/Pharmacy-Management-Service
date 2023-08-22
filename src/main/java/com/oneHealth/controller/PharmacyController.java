package com.oneHealth.controller;

import com.oneHealth.entity.Pharmacy;
import com.oneHealth.exception.DatabaseException;
import com.oneHealth.exception.PharmacyNotFoundException;
import com.oneHealth.service.PharmacyService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pharmacies")
public class PharmacyController {

    @Autowired
    private PharmacyService pharmacyService;

    @GetMapping("/getAllPharmacies")
    public ResponseEntity<List<Pharmacy>> getAllPharmacies() throws DatabaseException {
        try {
              List<Pharmacy> pharmacies = pharmacyService.getAllPharmacies();
              return ResponseEntity.ok(pharmacies);
        } catch (DatabaseException e) {
            throw e;
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pharmacy> getPharmacyById(@PathVariable long id) throws DatabaseException, PharmacyNotFoundException {
        try {
         

            Optional<Pharmacy> pharmacy = pharmacyService.getPharmacyById(id);

            if (pharmacy.isPresent()) {
              
                return ResponseEntity.ok(pharmacy.get());
            } else {
            
                throw new PharmacyNotFoundException("Pharmacy Not Found !!");
            }
        } catch (DatabaseException e) {
           
            throw e;
        } catch (PharmacyNotFoundException e) {
                       throw e;
        }
    }

    @PostMapping("/addPharmacies")
    public ResponseEntity<Pharmacy> saveLab(@RequestBody Pharmacy pharmacy) throws DatabaseException {
        try {
           	Pharmacy savedPharmacy = pharmacyService.savePharmacy(pharmacy);
                    return ResponseEntity.ok(savedPharmacy);
        } catch (DatabaseException e) {
                     throw e;
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updatePharmacy(@PathVariable long id, @RequestBody Pharmacy updatedPharmacy)
            throws DatabaseException, PharmacyNotFoundException {
        try {
           

        	pharmacyService.updatePharmacy(updatedPharmacy);

           
            return ResponseEntity.ok("Pharmacy Details  updated successfully.");
        } catch (DatabaseException e) {
         
            throw e;
        } catch (PharmacyNotFoundException e) {
           
            throw e;
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteLabById(@PathVariable long id) throws DatabaseException, PharmacyNotFoundException {
        try {
          
        	pharmacyService.deleteLabById(id);

           

            return ResponseEntity.ok("Pharmacy deleted successfully.");
        } catch (DatabaseException e) {
         
            throw e;
        } catch (PharmacyNotFoundException e) {
          
            throw e;
        }
    }
    
    
    
    @GetMapping("/pharmacies")
    public ResponseEntity<List<Pharmacy>> getPharmacyByCity(@RequestParam("city") String city) throws DatabaseException {
        try {
          

            List<Pharmacy> pharmacies = pharmacyService.getPharmacyByCity(city);

        

            return ResponseEntity.ok(pharmacies);
        } catch (DatabaseException e) {
    
            throw e;
        }
    }
}
