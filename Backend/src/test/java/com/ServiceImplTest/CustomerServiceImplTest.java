package com.ServiceImplTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.cg.movierentalapp.enitites.Address;
import com.cg.movierentalapp.enitites.Customer;
import com.cg.movierentalapp.exception.CustomException;
import com.cg.movierentalapp.repositories.CustomerRepository;
import com.cg.movierentalapp.service.CustomerServiceImpl;

public class CustomerServiceImplTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerServiceImpl customerService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddCustomer() {
        Customer customer = new Customer();
        customer.setCustomerId((short) 1);
        customer.setStoreId((byte) 1);
        customer.setFirstName("John");
        customer.setLastName("Doe");
        customer.setEmail("john.doe@example.com");
        Address address = new Address();
        address.setAddressId((short) 1);
        customer.setAddress(address);
        customer.setActive(1);
        customer.setCreateDate(new Timestamp(System.currentTimeMillis()));
        customer.setLastUpdate(new Timestamp(System.currentTimeMillis()));
        
        when(customerRepository.save(any(Customer.class))).thenReturn(customer);
        
        Customer result = customerService.addCustomer(customer);
        
        assertThat(result).isEqualTo(customer);
        verify(customerRepository, times(1)).save(any(Customer.class));
    }

    @Test
    public void testFindAllCustomers() {
        Customer customer1 = new Customer();
        customer1.setCustomerId((short) 1);
        customer1.setStoreId((byte) 1);
        customer1.setFirstName("John");
        customer1.setLastName("Doe");
        customer1.setEmail("john.doe@example.com");
        Address address1 = new Address();
        address1.setAddressId((short) 1);
        customer1.setAddress(address1);
        customer1.setActive(1);
        customer1.setCreateDate(new Timestamp(System.currentTimeMillis()));
        customer1.setLastUpdate(new Timestamp(System.currentTimeMillis()));
        Customer customer2 = new Customer();
        customer2.setCustomerId((short) 2);
        customer2.setStoreId((byte) 1);
        customer2.setFirstName("Jane");
        customer2.setLastName("Smith");
        customer2.setEmail("jane.smith@example.com");
        Address address2 = new Address();
        address2.setAddressId((short) 2);
        customer2.setAddress(address2);
        customer2.setActive(1);
        customer2.setCreateDate(new Timestamp(System.currentTimeMillis()));
        customer2.setLastUpdate(new Timestamp(System.currentTimeMillis()));
        List<Customer> customerList = Arrays.asList(customer1, customer2);
        
        when(customerRepository.findAll()).thenReturn(customerList);
        
        List<Customer> result = customerService.findAllCustomers();
        
        assertThat(result).isEqualTo(customerList);
        verify(customerRepository, times(1)).findAll();
    }

    @Test
    public void testFindCustomersByLastName() {
        String lastName = "Doe";
        Customer customer1 = new Customer();
        customer1.setCustomerId((short) 1);
        customer1.setStoreId((byte) 1);
        customer1.setFirstName("John");
        customer1.setLastName("Doe");
        customer1.setEmail("john.doe@example.com");
        Address address1 = new Address();
        address1.setAddressId((short) 1);
        customer1.setAddress(address1);
        customer1.setActive(1);
        customer1.setCreateDate(new Timestamp(System.currentTimeMillis()));
        customer1.setLastUpdate(new Timestamp(System.currentTimeMillis()));
        Customer customer2 = new Customer();
        customer2.setCustomerId((short) 2);
        customer2.setStoreId((byte) 1);
        customer2.setFirstName("Jane");
        customer2.setLastName("Doe");
        customer2.setEmail("jane.doe@example.com");
        Address address2 = new Address();
        address2.setAddressId((short) 2);
        customer2.setAddress(address2);
        customer2.setActive(1);
        customer2.setCreateDate(new Timestamp(System.currentTimeMillis()));
        customer2.setLastUpdate(new Timestamp(System.currentTimeMillis()));
        List<Customer> customerList = Arrays.asList(customer1, customer2);
        
        when(customerRepository.findByLastName(lastName)).thenReturn(customerList);
        
        List<Customer> result = customerService.findCustomersByLastName(lastName);
        
        assertThat(result).isEqualTo(customerList);
        verify(customerRepository, times(1)).findByLastName(lastName);
    }

    @Test
    public void testFindCustomersByFirstName() {
        String firstName = "John";
        Customer customer1 = new Customer();
        customer1.setCustomerId((short) 1);
        customer1.setStoreId((byte) 1);
        customer1.setFirstName("John");
        customer1.setLastName("Doe");
        customer1.setEmail("john.doe@example.com");
        Address address1 = new Address();
        address1.setAddressId((short) 1);
        customer1.setAddress(address1);
        customer1.setActive(1);
        customer1.setCreateDate(new Timestamp(System.currentTimeMillis()));
        customer1.setLastUpdate(new Timestamp(System.currentTimeMillis()));
        Customer customer2 = new Customer();
        customer2.setCustomerId((short) 2);
        customer2.setStoreId((byte) 1);
        customer2.setFirstName("John");
        customer2.setLastName("Smith");
        customer2.setEmail("john.smith@example.com");
        Address address2 = new Address();
        address2.setAddressId((short) 2);
        customer2.setAddress(address2);
        customer2.setActive(1);
        customer2.setCreateDate(new Timestamp(System.currentTimeMillis()));
        customer2.setLastUpdate(new Timestamp(System.currentTimeMillis()));
        List<Customer> customerList = Arrays.asList(customer1, customer2);
        
        when(customerRepository.findByFirstName(firstName)).thenReturn(customerList);
        
        List<Customer> result = customerService.findCustomersByFirstName(firstName);
        
        assertThat(result).isEqualTo(customerList);
        verify(customerRepository, times(1)).findByFirstName(firstName);
    }

    @Test
    public void testFindCustomerByEmail() {
        String email = "john.doe@example.com";
        Customer customer = new Customer();
        customer.setCustomerId((short) 1);
        customer.setStoreId((byte) 1);
        customer.setFirstName("John");
        customer.setLastName("Doe");
        customer.setEmail(email);
        Address address = new Address();
        address.setAddressId((short) 1);
        customer.setAddress(address);
        customer.setActive(1);
        customer.setCreateDate(new Timestamp(System.currentTimeMillis()));
        customer.setLastUpdate(new Timestamp(System.currentTimeMillis()));
        
        when(customerRepository.findByEmail(email)).thenReturn(customer);
        
        Customer result = customerService.findCustomerByEmail(email);
        
        assertThat(result).isEqualTo(customer);
        verify(customerRepository, times(1)).findByEmail(email);
    }
}
