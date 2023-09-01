package com.oneHealth.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.oneHealth.entity.Pharmacy;
import com.oneHealth.exception.DatabaseException;
import com.oneHealth.exception.PharmacyNotFoundException;
import com.oneHealth.repository.PharmacyRepository;
import com.oneHealth.service.PharmacyService;

/**
 * Implementation of the PharmacyService interface that provides the actual
 * business logic for managing pharmacies.
 */
@Service
public class PharmacyServiceImpl implements PharmacyService {

    // Logger for logging messages
    private static final Logger LOGGER = LoggerFactory.getLogger(PharmacyServiceImpl.class);

    @Autowired
    private PharmacyRepository pharmacyRepository;

    @Override
    public List<Pharmacy> getAllPharmacies() throws DatabaseException {
        LOGGER.info("Fetching all pharmacies.");
        // Retrieve a list of all pharmacies from the repository
        List<Pharmacy> pharmacies = pharmacyRepository.findAll();
        LOGGER.info("Fetched {} pharmacies.", pharmacies.size());

        return pharmacies;
    }

    @Override
    public Pharmacy savePharmacy(Pharmacy pharmacy) throws DatabaseException {
        LOGGER.info("Saving a new pharmacy.");
        // Save a pharmacy in the database using the repository
        Pharmacy savedPharmacy = pharmacyRepository.save(pharmacy);
        LOGGER.info("Saved pharmacy with ID: {}.", savedPharmacy.getPharmaId());

        return savedPharmacy;
    }

    @Override
    public Optional<Pharmacy> getPharmacyById(long pharmacyId) throws DatabaseException, PharmacyNotFoundException {
        LOGGER.info("Fetching pharmacy by ID: {}.", pharmacyId);
        // Retrieve a pharmacy by its ID from the repository
        Optional<Pharmacy> pharmacy = pharmacyRepository.findById(pharmacyId);

        if (pharmacy.isPresent()) {
            LOGGER.info("Fetched pharmacy with ID: {}.", pharmacyId);
            // If the pharmacy is found, return it
            return pharmacy;
        } else {
            LOGGER.error("Pharmacy Not Found with pharmacyId : {}.", pharmacyId);
            // If the pharmacy is not found, throw an exception
            throw new PharmacyNotFoundException("Pharmacy Not Found with pharmacyId : " + pharmacyId);
        }
    }

    @Override
    public void updatePharmacy(Pharmacy pharmacy) throws DatabaseException, PharmacyNotFoundException {
        LOGGER.info("Updating pharmacy with ID: {}.", pharmacy.getPharmaId());
        // Check if the pharmacy with the given ID exists in the repository
        boolean isValidId = pharmacyRepository.existsById(pharmacy.getPharmaId());
        if (isValidId) {
            // If the pharmacy exists, update it in the database
            pharmacyRepository.save(pharmacy);
            LOGGER.info("Updated pharmacy with ID: {}.", pharmacy.getPharmaId());
        } else {
            LOGGER.error("Pharmacy Not Found with pharmacyId : {}.", pharmacy.getPharmaId());
            // If the pharmacy does not exist, throw an exception
            throw new PharmacyNotFoundException("Pharmacy Not Found with pharmacyId : " + pharmacy.getPharmaId());
        }
    }

    @Override
    public void deleteLabById(long pharmacyId) throws DatabaseException, PharmacyNotFoundException {
        LOGGER.info("Deleting pharmacy with ID: {}.", pharmacyId);
        // Check if the pharmacy with the given ID exists in the repository
        boolean isValidId = pharmacyRepository.existsById(pharmacyId);
        if (isValidId) {
            // If the pharmacy exists, delete it from the database
            pharmacyRepository.deleteById(pharmacyId);
            LOGGER.info("Deleted pharmacy with ID: {}.", pharmacyId);
        } else {
            LOGGER.error("Pharmacy Not Found with pharmacyId : {}.", pharmacyId);
            // If the pharmacy does not exist, throw an exception
            throw new PharmacyNotFoundException("Pharmacy Not Found with pharmacyId : " + pharmacyId);
        }
    }

    @Override
    public List<Pharmacy> getPharmacyByCity(String city) throws DatabaseException {
        LOGGER.info("Fetching pharmacies in city: {}.", city);
        // Retrieve a list of pharmacies in a specific city from the repository
        List<Pharmacy> pharmacies = pharmacyRepository.findByCity(city);
        LOGGER.info("Fetched {} pharmacies in city: {}.", pharmacies.size(), city);

        return pharmacies;
    }
}
