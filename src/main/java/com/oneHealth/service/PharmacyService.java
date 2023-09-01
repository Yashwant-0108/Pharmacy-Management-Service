package com.oneHealth.service;

import java.util.List;
import java.util.Optional;
import com.oneHealth.entity.Pharmacy;
import com.oneHealth.exception.DatabaseException;
import com.oneHealth.exception.PharmacyNotFoundException;

/**
 * Service interface for managing Pharmacy entities.
 * This interface defines methods for performing various operations on Pharmacy entities.
 * Implementations of this interface will provide the actual business logic.
 */
public interface PharmacyService {

    /**
     * Retrieves a list of all pharmacies.
     *
     * @return A list of all pharmacies.
     * @throws DatabaseException If there is an issue with the database.
     */
    List<Pharmacy> getAllPharmacies() throws DatabaseException;

    /**
     * Saves a pharmacy in the database.
     *
     * @param pharmacy The pharmacy to be saved.
     * @return The saved pharmacy.
     * @throws DatabaseException If there is an issue with the database during the save operation.
     */
    Pharmacy savePharmacy(Pharmacy pharmacy) throws DatabaseException;

    /**
     * Retrieves a pharmacy by its ID.
     *
     * @param pharmacyId The ID of the pharmacy to retrieve.
     * @return An Optional containing the pharmacy if found, or empty if not found.
     * @throws DatabaseException If there is an issue with the database.
     * @throws PharmacyNotFoundException If the pharmacy with the given ID is not found.
     */
    Optional<Pharmacy> getPharmacyById(long pharmacyId) throws DatabaseException, PharmacyNotFoundException;

    /**
     * Updates the details of a pharmacy.
     *
     * @param pharmacy The updated pharmacy details.
     * @throws DatabaseException If there is an issue with the database during the update.
     * @throws PharmacyNotFoundException If the pharmacy to be updated is not found.
     */
    void updatePharmacy(Pharmacy pharmacy) throws DatabaseException, PharmacyNotFoundException;

    /**
     * Deletes a pharmacy by its ID.
     *
     * @param pharmacyId The ID of the pharmacy to delete.
     * @throws DatabaseException If there is an issue with the database during the delete operation.
     * @throws PharmacyNotFoundException If the pharmacy to be deleted is not found.
     */
    void deleteLabById(long pharmacyId) throws DatabaseException, PharmacyNotFoundException;

    /**
     * Retrieves a list of pharmacies in a specific city.
     *
     * @param city The city for which to retrieve pharmacies.
     * @return A list of pharmacies in the specified city.
     * @throws DatabaseException If there is an issue with the database.
     */
    List<Pharmacy> getPharmacyByCity(String city) throws DatabaseException;
}
