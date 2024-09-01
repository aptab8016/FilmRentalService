package com.cg.movierentalapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.cg.movierentalapp.enitites.Address;
import com.cg.movierentalapp.enitites.Customer;
import com.cg.movierentalapp.enitites.Staff;
import com.cg.movierentalapp.enitites.Store;
import com.cg.movierentalapp.service.IStoreService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class StoreController {
	private final IStoreService storeService;

	@Autowired
	public StoreController(IStoreService storeService) {
		this.storeService = storeService;
	}
    
	@GetMapping("/public/store")
	public Map<Object, Object> stores() {
		return storeService.findAllOfMyStore();
	}

	@GetMapping("/public/store/address")
	public List<Address> fetchAllAddress() {
		return this.storeService.fetchAllAddress();
	}

	@PutMapping("/admin/store/{id}/address")
    @PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<Store> assignAddressToStore(@PathVariable("id") byte storeId, @RequestBody Address address) {
		storeService.assignAddressToStore(storeId, address);
		Store store = storeService.getStoreById(storeId);
		return ResponseEntity.ok(store);
	}

	// working fine
	@GetMapping("/public/store/city/{city}")
	public ResponseEntity<List<Store>> getStoresByCity(@PathVariable("city") String city) {
		List<Store> stores = storeService.getStoresByCity(city);
		return ResponseEntity.ok(stores);
	}

	// working fine
	@GetMapping("/public/store/country/{country}")
	public ResponseEntity<List<Store>> getStoresByCountry(@PathVariable("country") String country) {
		List<Store> stores = storeService.getStoresByCountry(country);
		return ResponseEntity.ok(stores);
	}

	// working fine

	@GetMapping("/public/store/phone/{phone}")
	public ResponseEntity<Store> getStoreByPhoneNumber(@PathVariable("phone") String phone) {
		Store store = storeService.getStoreByPhoneNumber(phone);
		return ResponseEntity.ok(store);
	}

	// working fine
	@PutMapping("/admin/store/update/phone/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<Store> updateStorePhoneNumber(@PathVariable("id") byte storeId, @RequestBody String phone) {
		storeService.updateStorePhoneNumber(storeId, phone);
		Store store = storeService.getStoreById(storeId);
		return ResponseEntity.ok(store);
	}

	// working fine
	@PutMapping("/admin/store/{id}/manager")
    @PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<Store> assignManagerToStore(@PathVariable("id") byte managerStaffId,
			@RequestBody Staff manager) {
		storeService.assignManagerToStore(managerStaffId, manager);
		Store store = storeService.findStoreByManagerStaffId(managerStaffId);
		return ResponseEntity.ok(store);
	}

	// working fine
	@GetMapping("/admin/store/staff/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<List<Staff>> getAllStaffByStoreId(@PathVariable("id") byte storeId) {
		List<Staff> staffList = storeService.getAllStaffByStoreId(storeId);
		return ResponseEntity.ok(staffList);
	}

	@GetMapping("/admin/store/customer/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<List<Customer>> getCustomersByStoreId(@PathVariable("id") Byte storeId) {
		List<Customer> customers = storeService.getCustomersByStoreId(storeId);
		return ResponseEntity.ok(customers);
	}

//working fine
	@GetMapping("/admin/store/manager/{id}")
	public ResponseEntity<List<Staff>> getManagerDetailsByStoreId(@PathVariable("id") byte storeId) {
		List<Staff> managers = storeService.getManagerDetailsByStoreId(storeId);
		return ResponseEntity.ok(managers);
	}

//working fine
	@GetMapping("/admin/store/managers")
	public ResponseEntity<List<Staff>> getAllManagers() {
		List<Staff> managers = storeService.getAllManagers();
		return ResponseEntity.ok(managers);
	}
}