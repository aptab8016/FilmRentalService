package com.cg.movierentalapp.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import com.cg.movierentalapp.enitites.Customer;
import com.cg.movierentalapp.enitites.Film;
import com.cg.movierentalapp.enitites.Rental;

public interface RentalService {
	List<Rental> getAllRentals();
    List<Film> getFilmsRentedByCustomer(Long customerId);
    List<Film> getTopTenRentedFilms();
    List<Film> getTopTenRentedFilmsByStore(Long storeId);
//	List<Customer> getDueCustomer(long id);
    List<Customer> getCustomersWithPendingReturns(Long storeId);
    
    Rental rentFilm(Rental rental);
//    Rental updateReturnDate(Long id, Timestamp returnDate);
    Rental updateReturnDate(Integer rentalId);
    Rental addRental(Rental rental);
    
    
    }
    

