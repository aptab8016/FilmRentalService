package com.cg.movierentalapp.repositories;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cg.movierentalapp.enitites.Customer;
import com.cg.movierentalapp.enitites.Film;
import com.cg.movierentalapp.enitites.Rental;

public interface RentalRepository extends JpaRepository<Rental, Long> {

	@Query("SELECT r.inventory.film, COUNT(r) AS rentalCount " +
		       "FROM Rental r " +
		       "GROUP BY r.inventory.film " +
		       "ORDER BY rentalCount DESC " +
		       "LIMIT 10")
	    List<Film> findTop10RentedFilms();
	
	
	@Query("SELECT r.inventory.film " +
		       "FROM Rental r " +
		       "WHERE r.customer.customerId = :customerId")
	    List<Film> findFilmsRentedByCustomer(Long customerId);
	
	@Query("SELECT r.inventory.film, COUNT(r) AS rentalCount " +
	           "FROM Rental r " +
	           "WHERE r.inventory.store.storeId = :storeId " +
	           "GROUP BY r.inventory.film " +
	           "ORDER BY rentalCount DESC " +
	           "LIMIT 10")
	    List<Film> findTop10RentedFilmsByStore(Long storeId);
	

	 @Query("SELECT DISTINCT r.customer FROM Rental r WHERE r.returnDate IS NULL AND r.inventory.store.id = :storeId")
	    List<Customer> findCustomersWithPendingReturns(@Param("storeId") Long storeId);
	 
	 Rental findByRentalId(Integer rentalId);
//	 @Query("SELECT r FROM Rental r WHERE r.customer.id = :customerId")
//	    List<Rental> findRentalsByCustomerId(Integer customerId);
//        
//	 
//	    @Modifying
//	    @Query("UPDATE Rental r SET r.returnDate = :returnDate WHERE r.rentalId = :rentalId")
//	    void updateReturnDate(@Param("rentalId") Integer rentalId, @Param("returnDate") LocalDateTime returnDate);
	
}


