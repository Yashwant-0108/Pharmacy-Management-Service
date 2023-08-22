package com.oneHealth.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oneHealth.entity.Pharmacy;
import com.oneHealth.exception.DatabaseException;
import com.oneHealth.exception.PharmacyNotFoundException;
import com.oneHealth.repository.PharmacyRepository;
import com.oneHealth.service.PharmacyService;



@Service
public class PharmacyServiceImpl implements PharmacyService {

    @Autowired
    private PharmacyRepository pharmacyRepository;

    @Override
    public List<Pharmacy> getAllPharmacies() throws DatabaseException {
    	List<Pharmacy> labs = pharmacyRepository.findAll();

        return labs;
    }
    
    

    @Override
    public Pharmacy savePharmacy(Pharmacy pharmacy) throws DatabaseException {
       
    	Pharmacy savedPharmacy = pharmacyRepository.save(pharmacy);

         return savedPharmacy;
    	
    }

    @Override
    public Optional<Pharmacy> getPharmacyById(long pharmacyId) throws DatabaseException, PharmacyNotFoundException {
    	  Optional<Pharmacy> pharmacy = pharmacyRepository.findById(pharmacyId);

          if (pharmacy.isPresent()) {
           
              return pharmacy;
          } else {
          
              throw new PharmacyNotFoundException("Pharmacy Not Found with pharmacyId : " + pharmacyId);
          }
    }

    @Override
    public void updatePharmacy(Pharmacy pharmacy) throws DatabaseException, PharmacyNotFoundException {
    	 boolean isValidId = pharmacyRepository.existsById(pharmacy.getPharmaId());
         if (isValidId) {
             

        	 pharmacyRepository.save(pharmacy);

         } else {
         
             throw new PharmacyNotFoundException(" Pharma Not Found with lab_id : " + pharmacy.getPharmaId());
         }
    }

    @Override
    public void deleteLabById(long pharmacyId) throws DatabaseException, PharmacyNotFoundException {
    	 boolean isValidId = pharmacyRepository.existsById(pharmacyId);
         if (isValidId) {
       

             pharmacyRepository.deleteById(pharmacyId);

             
         } else {
          
             throw new PharmacyNotFoundException("Lab Not Found with lab_id : " + pharmacyId);
         }
    }

    @Override
    public List<Pharmacy> getPharmacyByCity(String city) throws DatabaseException {
    	

        List<Pharmacy> pharmacies = pharmacyRepository.findByCity(city);

        

        return pharmacies;
}
}
