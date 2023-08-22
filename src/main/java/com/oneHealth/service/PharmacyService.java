package com.oneHealth.service;

import java.util.List;
import java.util.Optional;

import com.oneHealth.entity.Pharmacy;
import com.oneHealth.exception.DatabaseException;
import com.oneHealth.exception.PharmacyNotFoundException;





public interface PharmacyService {
	
	 List<Pharmacy> getAllPharmacies() throws DatabaseException;
	 
	 Pharmacy savePharmacy(Pharmacy pharmacy) throws DatabaseException;
	 
	 Optional<Pharmacy> getPharmacyById(long pharmacyId) throws DatabaseException, PharmacyNotFoundException;
	 
	 void updatePharmacy(Pharmacy pharmacy) throws DatabaseException, PharmacyNotFoundException;
	  
	 
	 void deleteLabById(long pharmacyId) throws DatabaseException, PharmacyNotFoundException;
	 
	 List<Pharmacy> getPharmacyByCity(String city) throws DatabaseException;

}
