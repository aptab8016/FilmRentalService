package com.cg.movierentalapp.service;

import java.util.List;
import java.util.Map;

import com.cg.movierentalapp.enitites.Payment;

public interface IPaymentService {

	List<Map<String, Object>> getRevenueByDate();

	List<Map<String, Object>> getRevenueByDateAndStore(int storeId);

	List<Map<String,Object>> getRevenueByFilm();

//	List<Map<String,Object>> getRevenueByFilmAndStore(int storeId);
//	List<Map<String, Object>> getFilmRevenueByStore(int filmId);

	List<Payment> getLatest100Payments();
	
	
	
	
	
	
	List<Map<String,Object>> getRevenueByFilmAndStoreId(int StoreId);

	String addPayment(Payment payment);
	 List<Payment> getAllPayments();

	List<Object[]> calculateRevenueByFilmStoreWise(int filmId);
	
	

}
