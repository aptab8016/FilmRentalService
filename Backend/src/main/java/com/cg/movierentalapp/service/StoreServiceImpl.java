package com.cg.movierentalapp.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.movierentalapp.enitites.Address;
import com.cg.movierentalapp.enitites.Customer;
import com.cg.movierentalapp.enitites.Staff;
import com.cg.movierentalapp.enitites.Store;
import com.cg.movierentalapp.exception.CustomException;
import com.cg.movierentalapp.repositories.AddressRepository;
import com.cg.movierentalapp.repositories.CustomerRepository;
import com.cg.movierentalapp.repositories.StaffRepository;
import com.cg.movierentalapp.repositories.StoreRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

@Service
public class StoreServiceImpl implements IStoreService {

	@Autowired
	private StoreRepository storeRepository;

	@Autowired
	private AddressRepository addressRepository;

	@Autowired
	private StaffRepository staffRepository;

	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public void updateStore(Store store) {
		storeRepository.save(store);
	}

	@Override
	public Store getStoreById(byte id) throws CustomException {
		Store store = storeRepository.findByStoreId(id);

		if (store == null) {
			throw new CustomException("No store found with the id: " + id);
		}

		return store;
	}

	// working fine
	@Override
	public List<Store> getStoresByCity(String city) throws CustomException {
		List<Store> list = storeRepository.findByAddressCityCity(city);
		if (!list.isEmpty()) {
			return list;
		} else {
			throw new CustomException("Store are not found.. ");
		}
	}

	// working fine
	@Override
	public List<Store> getStoresByCountry(String country) throws CustomException {
		List<Store> list = storeRepository.findByAddressCityCountryCountry(country);
		if (!list.isEmpty()) {
			return list;
		} else {
			throw new CustomException("Store are not found.. ");
		}
	}

	@Override
	public Store getStoreByPhoneNumber(String phone) throws CustomException {
		Store store = storeRepository.findByAddressPhone(phone);

		if (store == null) {
			throw new CustomException("No store found with the phone number: " + phone);
		}

		return store;
	}

//
	@Override
	public void assignAddressToStore(byte storeId, Address address) throws CustomException {
		Store store = storeRepository.findByStoreId(storeId);
		if (store == null) {
			throw new CustomException("No store found with the storeId: " + storeId);
		}
		store.setAddress(address);
		addressRepository.save(address);

	}

	@Override
	public void updateStorePhoneNumber(byte storeId, String phone) throws CustomException {
		Optional<Store> storeOptional = storeRepository.findById(storeId);
		if (storeOptional.isPresent()) {
			Store store = storeOptional.get();
			store.getAddress().setPhone(phone);
			storeRepository.save(store);
		} else {
			throw new CustomException("No store found with the storeId: " + storeId);
		}
	}

	// working fine
	@Override
	public void assignManagerToStore(byte managerStaffId, Staff manager) throws CustomException {
		Store store = findStoreByManagerStaffId(managerStaffId);
		if (store == null) {
			throw new CustomException("No store found with the managerStaffId: " + managerStaffId);
		}
		manager.setStore(store);
	}

	@Override
	public List<Staff> getAllStaffByStoreId(byte storeId) throws CustomException {

		List<Staff> list = staffRepository.getByStoreStoreId(storeId);
		if (!list.isEmpty()) {
			return list;
		} else {
			throw new CustomException("Staff are not found.. ");
		}
	}

	@Override
	public List<Staff> getManagerDetailsByStoreId(byte storeId) throws CustomException {
		List<Staff> list = staffRepository.findByStoreId(storeId);
		if (!list.isEmpty()) {
			return list;
		} else {
			throw new CustomException("ManagerDetail are not found.. ");
		}
	}

	@Override
	public List<Staff> getAllManagers() throws CustomException {

		List<Staff> list = staffRepository.allManagers();
		if (!list.isEmpty()) {
			return list;
		} else {
			throw new CustomException("Manager are not found.. ");
		}
	}

	@Override
	public List<Store> fetchAllStore() throws CustomException {
		List<Store> stores = storeRepository.findAll();
		List<Store> list = stores;
		if (!list.isEmpty()) {
			return list;
		} else {
			throw new CustomException("Store are not found.. ");
		}
	}

//E

	@Override
	public Store findStoreByManagerStaffId(byte managerStaffId) throws CustomException {
		Staff staff = staffRepository.findByStaffId(managerStaffId);

		if (staff == null) {
			throw new CustomException("No staff found with the given managerStaffId: " + managerStaffId);
		}

		byte id = staff.getStaffId();

		Store store = storeRepository.findByStoreId(id);

		if (store == null) {
			throw new CustomException("No store found with the managerStaffId: " + managerStaffId);
		}

		return store;

	}

	@PersistenceContext
	private EntityManager entityManager;

	public Map<Object, Object> findAllOfMyStore() throws CustomException {
		String jpql = "SELECT s.storeId, s.address.addressId, s.lastUpdate, s.staff.staffId FROM Store s";
		TypedQuery<Object[]> query = entityManager.createQuery(jpql, Object[].class);

		List<Object[]> objlist = query.getResultList();
		if (objlist.isEmpty()) {
			throw new CustomException("No stores found.");
		}

		Object[] arr = objlist.get(0);

		Object[] objarr = { "store_id", "manager_staff_id", "last_update", "address_id" };

		int count = 0;
		HashMap<Object, Object> map = new HashMap<>();
		for (Object obj : objarr) {
			map.put(obj, arr[count++]);
		}

		return map;

	}

	@Override
	public List<Address> fetchAllAddress() throws CustomException {
		List<Address> addresses = addressRepository.findAll();

		List<Address> list = addresses;
		if (!list.isEmpty()) {
			return list;
		} else {
			throw new CustomException("Address are not found.. ");
		}

	}

	@Override
	public List<Staff> findAllStaff() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Store> findAllStore() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Customer> getCustomersByStoreId(Byte storeId) throws CustomException {
		List<Customer> list = storeRepository.findCustomersByStoreId(storeId);
		if (!list.isEmpty()) {
			return list;
		} else {
			throw new CustomException("Customer are not found.. ");
		}
	}

}
