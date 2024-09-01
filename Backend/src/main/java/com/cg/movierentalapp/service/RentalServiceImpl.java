package com.cg.movierentalapp.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.movierentalapp.enitites.Customer;
import com.cg.movierentalapp.enitites.Film;
import com.cg.movierentalapp.enitites.Rental;
import com.cg.movierentalapp.exception.CustomException;
import com.cg.movierentalapp.repositories.InventoryRepository;
import com.cg.movierentalapp.repositories.RentalRepository;

@Service
public class RentalServiceImpl implements RentalService {

	private final RentalRepository rentalRepository;

	@Autowired
	public RentalServiceImpl(RentalRepository rentalRepository) {
		this.rentalRepository = rentalRepository;
	}

	@Autowired
	public InventoryRepository inventoryRepository;

	@Override
	public List<Rental> getAllRentals()throws CustomException {
		List<Rental> list = rentalRepository.findAll();
		if (!list.isEmpty()) {
			return list;
		} else {
			throw new CustomException("Rentals are not found.. ");
		}
	}

	@Override
	public List<Film> getTopTenRentedFilms()throws CustomException {
		List<Film> list = rentalRepository.findTop10RentedFilms();
		if (!list.isEmpty()) {
			return list;
		} else {
			throw new CustomException("fIlms are not found.. ");
		}
	}

	@Override
	public List<Film> getFilmsRentedByCustomer(Long customerId)throws CustomException {
		List<Film> list = rentalRepository.findFilmsRentedByCustomer(customerId);
		if (!list.isEmpty()) {
			return list;
		} else {
			throw new CustomException("fIlms are not found.. ");
		}
	}

	@Override
	public List<Film> getTopTenRentedFilmsByStore(Long storeId)throws CustomException {
		List<Film> list = rentalRepository.findTop10RentedFilmsByStore(storeId);
		if (!list.isEmpty()) {
			return list;
		} else {
			throw new CustomException("fIlms are not found.. ");
		}
	}

	@Override
	public List<Customer> getCustomersWithPendingReturns(Long storeId)throws CustomException {
		List<Customer> list = rentalRepository.findCustomersWithPendingReturns(storeId);
		if (!list.isEmpty()) {
			return list;
		} else {
			throw new CustomException("fIlms are not found.. ");
		}
	}

	@Override
	public Rental rentFilm(Rental rental) {
		rental.setRentalDate(new Timestamp(System.currentTimeMillis()));

		rental.setLastUpdate(new Timestamp(System.currentTimeMillis()));

		return rentalRepository.save(rental);
	}

	@Override
	public Rental updateReturnDate(Integer rentalId)throws CustomException {
		Rental rental = rentalRepository.findByRentalId(rentalId);
		if (rental != null) {
			rental.setReturnDate(Timestamp.valueOf(LocalDateTime.now()));
			rental.setLastUpdate(Timestamp.valueOf(LocalDateTime.now()));
			return rentalRepository.save(rental);
		}

		throw new CustomException("Rental not found with rentalId: " + rentalId);
	}

	@Override
	public Rental addRental(Rental rental) {
		if (rental == null) {
			throw new CustomException("Rental object is null");
		}
		Rental savedRental = rentalRepository.save(rental);
		return savedRental;
	}
	
//	@Override
//    public List<Film> getFilmsRentedByCustomer(Long customerId) {
//        List<Film> list = rentalRepository.findFilmsRentedByCustomer(customerId);
//        if (list.size() > 0) {
//             return list;
//         } else {
//             throw new CustomException("fIlms are not found.. ");
//         }
//    }
}