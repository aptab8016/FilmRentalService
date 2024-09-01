
package com.cg.movierentalapp.service;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.cg.movierentalapp.enitites.Address;

import com.cg.movierentalapp.enitites.Customer;

import com.cg.movierentalapp.exception.CustomException;

import com.cg.movierentalapp.repositories.CustomerRepository;

@Service

public class CustomerServiceImpl implements CustomerService {

	@Autowired

	private CustomerRepository repo;

	private CustomerService custservice;

	@Override

	public List<Customer> findAllCustomers()throws CustomException {

		List<Customer> list = repo.findAll();

		if (!list.isEmpty()) {
			return list;
		} else {
			throw new CustomException("Customers are not found.. ");
		}

	}

	@Override

	public List<Customer> findCustomersByLastName(String lastName)throws CustomException {

		List<Customer> list = repo.findByLastName(lastName);

		if (!list.isEmpty()) {

			return list;

		} else {

			throw new CustomException("Customer is not found for this lastname ");

		}

	}

	@Override

	public List<Customer> findCustomersByFirstName(String firstName)throws CustomException {

		List<Customer> list = repo.findByFirstName(firstName);

		if (!list.isEmpty()) {

			return list;

		} else {

			throw new CustomException("Customer is not found for this firstname");

		}

	}

	@Override

	public Customer findCustomerByEmail(String email)throws CustomException {

		Customer customer = repo.findByEmail(email);

		if (customer == null) {

			throw new CustomException("Customer not found for email: " + email);

		}

		return customer;

	}

	@Override

	public List<Customer> getCustomersByCity(String city)throws CustomException {

		List<Customer> list = repo.findByAddress_City_City(city);

		if (!list.isEmpty()) {

			return list;

		} else {

			throw new CustomException("Customer is not found for this city");

		}

	}

	@Override

	public List<Customer> getCustomersByCountry(String country)throws CustomException {

		List<Customer> list = repo.findByAddress_City_Country_Country(country);

		if (!list.isEmpty()) {

			return list;

		} else {

			throw new CustomException("Customer is not found for this country ");

		}

	}

	@Override

	public List<Customer> findActiveCustomers()throws CustomException {

		List<Customer> list = repo.findByActive(1);

		if (!list.isEmpty()) {

			return list;

		} else {

			throw new CustomException("Active customers are not found ");

		}

	}

	@Override

	public Customer assignAddressToCustomer(short customerId, Address address)throws CustomException {

		Customer customer = repo.findById(customerId).orElse(null);

		if (customer == null) {

			throw new CustomException("Customer not found for ID: " + customerId);

		} else {

			customer.setAddress(address);

			return repo.save(customer);

		}

	}

//city

	@Override

	public List<Customer> findInactiveCustomers()throws CustomException {

		List<Customer> list = repo.findByActive(0);

		if (!list.isEmpty()) {

			return list;

		} else {

			throw new CustomException("Active customers not found ");

		}

	}

	@Override

	public Customer findCustomerByPhone(String phone)throws CustomException {

		Customer customer = repo.findByAddressPhone(phone);

		if (customer == null) {

			throw new CustomException("Customer not found for phone number: " + phone);

		}

		return customer;

	}

	@Override

	public Customer updateCustomerFirstName(Short id, String firstName)throws CustomException {

		Optional<Customer> optionalCustomer = repo.findById(id);

		if (optionalCustomer.isEmpty()) {

			throw new CustomException("Customer not found for ID: " + id);

		}

		Customer customer = optionalCustomer.get();

		customer.setFirstName(firstName);

		return repo.save(customer);

	}

	@Override

	public Customer updateCustomerLastName(Short id, String lastName)throws CustomException {

		Optional<Customer> optionalCustomer = repo.findById(id);

		if (optionalCustomer.isPresent()) {

			Customer customer = optionalCustomer.get();

			customer.setLastName(lastName);

			return repo.save(customer);

		}

		else {

			throw new CustomException("Customer not found for ID: " + id);

		}

	}

	@Override

	public Customer updateCustomerEmail(Short id, String email)throws CustomException {

		Optional<Customer> optionalCustomer = repo.findById(id);

		if (optionalCustomer.isPresent()) {

			Customer customer = optionalCustomer.get();

			customer.setEmail(email);

			return repo.save(customer);

		}

		else {

			throw new CustomException("Customer not found for ID: " + id);

		}

	}

	@Override

	public Optional<Customer> assignStoreToCustomer(Short id, Byte storeId)throws CustomException {

		Optional<Customer> optionalCustomer = repo.findById(id);

		if (optionalCustomer.isPresent()) {

			Customer customer = optionalCustomer.get();

			customer.setStoreId(storeId);

			Customer updatedCustomer = repo.save(customer);

			return Optional.of(updatedCustomer);

		}

		else {

			throw new CustomException("Customer not found for ID: " + id);

		}

	}

	@Override

	public Customer updateCustomerPhone(Short customerId, String phone)throws CustomException {

		Customer customer = repo.findById(customerId).orElse(null);

		if (customer != null) {

			Address address = customer.getAddress();

			if (address != null) {

				address.setPhone(phone);

				return repo.save(customer);

			}

			else {

				throw new CustomException("Address not found for Customer ID: " + customerId);

			}

		} else {

			throw new CustomException("Customer not found for ID: " + customerId);

		}

	}

	/////////// ****************POST REMAINING******/////////////////////////////

	@Override

	public Customer addCustomer(Customer customer) {

		return repo.save(customer);

	}

	@Override

	public Customer saveCustomer(Customer customer) {

		return repo.save(customer);

	}

//     @Override

//     public Customer addCustomer(Customer customer)throws CustomException {

//         if (customer == null) {

//             throw new CustomException("Customer object cannot be null.");

//         }

//         return repo.save(customer);

//     }

//

//     @Override

//     public Customer saveCustomer(Customer customer)throws CustomException {

//         if (customer == null) {

//             throw new CustomException("Customer object cannot be null.");

//         }

//         return repo.save(customer);

//     }
    @Override
    public Customer getCustomerById(short id) {

        return repo.findById(id).get();
    }

}
