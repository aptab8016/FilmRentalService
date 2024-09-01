package com.cg.movierentalapp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.movierentalapp.enitites.Customer;
import com.cg.movierentalapp.enitites.Film;
import com.cg.movierentalapp.enitites.Rental;
import com.cg.movierentalapp.service.RentalService;

@RestController
@RequestMapping("/api/v1")
public class RentalController {

	private final RentalService rentalService;

	@Autowired
	public RentalController(RentalService rentalService) {
		this.rentalService = rentalService;
	}

	@GetMapping("/public/rental/getAll")
	public ResponseEntity<List<Rental>> getAllRentals() {
		List<Rental> rentals = rentalService.getAllRentals();
		return new ResponseEntity<>(rentals, HttpStatus.OK);
	}

	@GetMapping("/public/rental/toptenfilms")
	public ResponseEntity<List<Film>> getTopTenRentedFilms() {
		List<Film> films = rentalService.getTopTenRentedFilms();
		return new ResponseEntity<>(films, HttpStatus.OK);
	}

	@GetMapping("/customer/rental/customer/{id}")
	@PreAuthorize("hasAuthority('CUSTOMER')")
	public ResponseEntity<List<Film>> getFilmsRentedByCustomer(@PathVariable("id") Long customerId) {
		List<Film> films = rentalService.getFilmsRentedByCustomer(customerId);
		return new ResponseEntity<>(films, HttpStatus.OK);
	}

	@GetMapping("/public/rental/toptenfilms/store/{id}")
	public ResponseEntity<List<Film>> getTopTenRentedFilmsByStore(@PathVariable("id") Long storeId) {
		List<Film> films = rentalService.getTopTenRentedFilmsByStore(storeId);
		return new ResponseEntity<>(films, HttpStatus.OK);
	}

	@GetMapping("/admin/rental/due/store/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<List<Customer>> getCustomersWithPendingReturns(@PathVariable("id") Long storeId) {
		List<Customer> customers = rentalService.getCustomersWithPendingReturns(storeId);
		return new ResponseEntity<>(customers, HttpStatus.OK);
	}

	@PutMapping("/admin/rental/update/returndate/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<Rental> updateReturnDate(@PathVariable("id") Integer rentalId) {
		Rental updatedRental = rentalService.updateReturnDate(rentalId);
		return ResponseEntity.ok(updatedRental);
	}

	// -----------------post-------------------
	@PostMapping("/admin/rental/add")
  @PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<Rental> addRental(@RequestBody Rental rental) {
		Rental response = rentalService.addRental(rental);
		return ResponseEntity.ok(response);
	}
	
//	@GetMapping("/public/rental/customer/{id}")
//    public ResponseEntity<List<Film>> getFilmsRentedByCustomer(@PathVariable("id") Long customerId) {
//        List<Film> films = rentalService.getFilmsRentedByCustomer(customerId);
//        return new ResponseEntity<>(films, HttpStatus.OK);
//    }

}
