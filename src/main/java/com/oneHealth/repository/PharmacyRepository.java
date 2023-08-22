package com.oneHealth.repository;

import com.oneHealth.entity.Pharmacy;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PharmacyRepository extends JpaRepository<Pharmacy, Long> {
    
	
	 List<Pharmacy> findByCity(String city);
}
