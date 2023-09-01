package com.oneHealth.repository;

import com.oneHealth.entity.Pharmacy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repository interface for managing Pharmacy entities.
 * This interface extends JpaRepository for CRUD operations on Pharmacy entities.
 * It provides methods to interact with the database for Pharmacy-related operations.
 * Implementations are provided by the Spring Data JPA framework.
 */
public interface PharmacyRepository extends JpaRepository<Pharmacy, Long> {

    /**
     * Find pharmacies by city.
     *
     * @param city The city where the pharmacies are located.
     * @return A list of pharmacies in the specified city.
     */
    List<Pharmacy> findByCity(String city);
}
