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

import com.cg.movierentalapp.enitites.Address;
import com.cg.movierentalapp.enitites.Staff;
import com.cg.movierentalapp.enitites.Store;
import com.cg.movierentalapp.service.StaffServiceImpl;

@RestController
@RequestMapping("/api/v1")
public class StaffController {

	@Autowired
	private StaffServiceImpl staffService;

	// done
	@PostMapping("/admin/staff/post")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<String> addStaff(@RequestBody Staff staff) {
		Staff newStaff = staffService.addStaff(staff);
			return new ResponseEntity<>("Record Created Successfully", HttpStatus.CREATED);
	}

	@GetMapping("/admin/staff")
	@PreAuthorize("hasAuthority('ADMIN')")
	public List<Staff> fetchAll() {
		return staffService.fetchAll();
	}

	// done
	@GetMapping("/admin/staff/lastname/{ln}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public List<Staff> searchStaffByLastName(@PathVariable String ln) {
		return staffService.searchStaffByLastName(ln);
	}

	// done
	@GetMapping("/admin/staff/firstname/{fn}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public List<Staff> searchStaffByFirstName(@PathVariable String fn) {
		return staffService.searchStaffByFirstName(fn);
	}

	// done
	@GetMapping("/admin/staff/email/{email}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public Staff searchStaffByEmail(@PathVariable String email) {
		return staffService.searchStaffByEmail(email);
	}

	@PutMapping("/admin/staff/{id}/address")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<Staff> assignAddressToStaff(@PathVariable("id") byte staffId, @RequestBody Address address) {
		// Get the staff by ID
		Staff staff = staffService.getStaffById(staffId);
		// Assign the address to the staff
		staff.setAddress(address);
		// Update the staff
		Staff updatedStaff = staffService.updateStaff(staff);
		return new ResponseEntity<>(updatedStaff, HttpStatus.OK);
	}

	// done
	@GetMapping("/admin/staff/city/{city}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public List<Staff> searchStaffByCity(@PathVariable String city) {
		return staffService.searchStaffByCity(city);
	}

	// done
	@GetMapping("/admin/staff/country/{country}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public List<Staff> searchStaffByCountry(@PathVariable String country) {
		return staffService.searchStaffByCountry(country);
	}

	// done
	@GetMapping("/admin/staff/phone/{phone}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public Staff searchStaffByPhone(@PathVariable String phone) {
		return staffService.searchStaffByPhone(phone);
	}

	// done
	@PutMapping("/admin/staff/update/fn/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public Staff updateStaffFirstName(@PathVariable byte id, @RequestBody Staff staff) {
		// Update the first name of the staff object
		staff.setFirstName(staff.getFirstName());
		// Pass the updated staff object to the service method
		return staffService.updateStaffFirstName(id, staff.getFirstName());
	}

	// done
	@PutMapping("/admin/staff/update/ln/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public Staff updateStaffLastName(@PathVariable byte id, @RequestBody Staff staff) {
		// Update the last name of the staff object
		staff.setLastName(staff.getLastName());

		// Pass the updated staff object to the service method
		return staffService.updateStaffLastName(id, staff.getLastName());
	}

	// done
	@PutMapping("/admin/staff/update/email/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public Staff updateStaffEmail(@PathVariable byte id, @RequestBody Staff staff) {
		// Update the email of the staff object
		staff.setEmail(staff.getEmail());

		// Pass the updated staff object to the service method
		return staffService.updateStaffEmail(id, staff.getEmail());
	}

	@PutMapping("/admin/staff/update/store/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public Staff assignStoreToStaff(@PathVariable byte id, @RequestBody Store store) {
		return staffService.assignStoreToStaff(id, store);
	}

	@PutMapping("/admin/staff/update/phone/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public Staff updateStaffPhone(@PathVariable byte id, @RequestBody String phone) {
		return staffService.updateStaffPhone(id, phone);
	}

}
