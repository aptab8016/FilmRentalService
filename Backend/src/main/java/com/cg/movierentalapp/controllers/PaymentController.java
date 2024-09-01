package com.cg.movierentalapp.controllers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.movierentalapp.enitites.Payment;
import com.cg.movierentalapp.repositories.PaymentRepository;
import com.cg.movierentalapp.service.IPaymentService;


@RestController
@RequestMapping("/api/v1")
public class PaymentController {

	private IPaymentService paymentService;

	@Autowired
	public PaymentController(IPaymentService paymentService) {
		this.paymentService = paymentService;
	}

	@GetMapping("/admin/payment/revenue/datewise")
    @PreAuthorize("hasAuthority('ADMIN')")
	public List<Map<String, Object>> getRevenueByDate() {
		return paymentService.getRevenueByDate();
	}

	@GetMapping("/admin/payment/revenue/datewise/store/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
	public List<Map<String,Object>> getRevenueByDateAndStore(@PathVariable("id") int id) {
		return paymentService.getRevenueByDateAndStore(id);
	}

	@GetMapping("/admin/payment/revenue/filmwise")
    @PreAuthorize("hasAuthority('ADMIN')")
	public List<Map<String,Object>> getRevenueByFilm() {
		return paymentService.getRevenueByFilm();
	}
	
    @GetMapping("/admin/payment/revenue/film/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<Map<String, Object>>> getFilmRevenueStoreWise(@PathVariable("id") int filmId) {
        List<Object[]> revenueData = paymentService.calculateRevenueByFilmStoreWise(filmId);
        
        List<Map<String, Object>> response = new ArrayList<>();
        for (Object[] result : revenueData) {
            Map<String, Object> entry = new HashMap<>();
            entry.put("store", result[0]);
            entry.put("amount", result[1]);
            response.add(entry);
        }
        
        return ResponseEntity.ok(response);
    }
    
	@GetMapping("/admin/payment/revenue/films/store/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
	public List<Map<String,Object>> getRevenueByFilmAndStoreId(@PathVariable int id) {
		return paymentService.getRevenueByFilmAndStoreId(id);
	}
	
	@GetMapping("/admin/payment/getall")
   @PreAuthorize("hasAuthority('ADMIN')")
    public List<Payment> getAllPayments() {
        return paymentService.getAllPayments();
    }
	
	@GetMapping("/admin/payment/getpayments")
	   @PreAuthorize("hasAuthority('ADMIN')")
    public List<Payment> getLatest200Payments() {
        return paymentService.getLatest100Payments();
    }
	
	//---------------post----------------------------
	@PostMapping("/admin/payment/add")
	@PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> addPayment(@RequestBody Payment payment) {
        String result = paymentService.addPayment(payment);
        return ResponseEntity.ok(result);
    }
}
