package com.cg.movierentalapp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cg.movierentalapp.enitites.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Short> {
	List<Customer> findByLastName(String lastName);
	List<Customer> findByFirstName(String firstName);
	Customer findByEmail(String email);	
	List<Customer> findByActive(int active);
	List<Customer> findByAddress_City_City(String city);
	List<Customer> findByAddress_City_Country_Country(String country);
	Customer findByAddressPhone(String phone);
	
	//ashish
//	@Query("SELECT c FROM Customer c WHERE c.store.id = :storeId")
//
//    List<Customer> findCustomersByStoreId(@Param("storeId") byte storeId);

	
	

}
