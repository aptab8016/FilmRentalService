package com.cg.movierentalapp.controllers;


import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cg.movierentalapp.enitites.Address;
import com.cg.movierentalapp.enitites.Customer;
import com.cg.movierentalapp.enitites.Staff;
import com.cg.movierentalapp.service.CustomerService;

@RestController
@RequestMapping("/api/v1")
@Validated
public class CustomerController {
	
	@Autowired
	private CustomerService customerservice;
	
    @PostMapping("/admin/customer/post")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> addCustomer(@RequestBody Customer customer) {
        Customer addedCustomer = customerservice.addCustomer(customer);
            return new ResponseEntity<>("Record Created Successfully", HttpStatus.OK);
    }
	
	@GetMapping("/admin/customer/getAll")
   @PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<?> getAllCustomers(){
		return new ResponseEntity<>(customerservice.findAllCustomers(), HttpStatus.OK);
	}
	
	//get by lastname
	@GetMapping("/admin/customer/lastname/{ln}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<Customer>> searchCustomersByLastName(@PathVariable("ln") String lastName) {
        List<Customer> customers = customerservice.findCustomersByLastName(lastName);
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }
	
	// get by first Name
	@GetMapping("/admin/customer/firstname/{fn}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<Customer>> searchCustomersByFirstName(@PathVariable("fn") String firstName) {
        List<Customer> customers = customerservice.findCustomersByFirstName(firstName);
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }
	
	@GetMapping("/admin/customer/email/{email}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Customer> searchCustomerByEmail(@PathVariable("email") String email) {
        Customer customer = customerservice.findCustomerByEmail(email);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }
	
	@GetMapping("/admin/customer/city/{city}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<Customer> getCustomersByCity(@PathVariable("city") String city) {
        return customerservice.getCustomersByCity(city);
    }
	
	@GetMapping("/admin/customer/country/{country}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<Customer> getCustomersByCountry(@PathVariable("country") String country) {
        return customerservice.getCustomersByCountry(country);
    }
	
	@PutMapping("/admin/customer/{id}/address")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Customer> assignAddressToCustomer(@PathVariable("id") short customerId,
            @RequestBody Address address) {
        Customer customer = customerservice.assignAddressToCustomer(customerId, address);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }
	
	
    @GetMapping("/admin/customer/active")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<Customer>> searchActiveCustomers() {
        List<Customer> customers = customerservice.findActiveCustomers();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }
    
    
    @GetMapping("/admin/customer/inactive")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<Customer>> searchInactiveCustomers() {
        List<Customer> customers = customerservice.findInactiveCustomers();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }
    
    @GetMapping("/admin/customer/phone/{phone}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Customer> searchCustomerByPhone(@PathVariable("phone") String phone) {
        Customer customer = customerservice.findCustomerByPhone(phone);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @PutMapping("/admin/customer/update/fn/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Customer> updateCustomerFirstName(
            @PathVariable("id") Short id,
            @RequestBody Map<String, String> requestBody) {
        String firstName = requestBody.get("firstName");
        Customer updatedCustomer = customerservice.updateCustomerFirstName(id, firstName);
        return ResponseEntity.ok(updatedCustomer);
    }
    
//	public Staff updateStaffFirstName(@PathVariable byte id, @RequestBody Staff staff) {
//		// Update the first name of the staff object
//		staff.setFirstName(staff.getFirstName());
//		// Pass the updated staff object to the service method
//		return staffService.updateStaffFirstName(id, staff.getFirstName());
//	}
    
    @PutMapping("/admin/customer/update/ln/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Customer> updateCustomerLastName(
            @PathVariable("id") Short id,
            @RequestBody Map<String, String> requestBody) {
        String lastName = requestBody.get("lastName");
        Customer updatedCustomer = customerservice.updateCustomerLastName(id, lastName);
        return ResponseEntity.ok(updatedCustomer);
    }
    
    
    @PutMapping("/admin/customer/update/email/{id}")
   @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Customer> updateCustomerEmail(
            @PathVariable("id") Short id,
            @RequestBody Map<String, String> requestBody) {
        String email = requestBody.get("email");
        Customer updatedCustomer = customerservice.updateCustomerEmail(id, email);
        return ResponseEntity.ok(updatedCustomer);
    }
    

    @PutMapping("/admin/customer/update/store/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Customer> assignStoreToCustomer(
            @PathVariable("id") Short id,
            @RequestParam("storeId") Byte storeId) {
        Optional<Customer> updatedCustomer = customerservice.assignStoreToCustomer(id, storeId);
            return new ResponseEntity<>(updatedCustomer.get(), HttpStatus.OK);
    }
    
    @PutMapping("/admin/customer/update/phone/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Customer> updateCustomerPhone(
            @PathVariable("id") Short customerId,
            @RequestParam("phone") String phone) {
        Customer updatedCustomer = customerservice.updateCustomerPhone(customerId, phone);
        return new ResponseEntity<>(updatedCustomer, HttpStatus.OK);
    }
    
    @GetMapping("customer/customer/{id}")
    @PreAuthorize("hasAuthority('CUSTOMER')")
    public Customer getCustomerById(@PathVariable short id) {
        return customerservice.getCustomerById(id);
    }
}
	

