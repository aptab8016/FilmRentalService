package com.ControllerTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.cg.movierentalapp.controllers.StaffController;
import com.cg.movierentalapp.enitites.Address;
import com.cg.movierentalapp.enitites.Staff;
import com.cg.movierentalapp.service.StaffServiceImpl;

public class StaffControllerTest {

    @Mock
    private StaffServiceImpl staffService;

    @InjectMocks
    private StaffController staffController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSearchStaffByLastName() {
        String lastName = "Doe";
        Staff staff1 = new Staff();
        staff1.setStaffId((byte) 1);
        staff1.setFirstName("John");
        staff1.setLastName("Doe");
        Staff staff2 = new Staff();
        staff2.setStaffId((byte) 2);
        staff2.setFirstName("Jane");
        staff2.setLastName("Doe");
        List<Staff> staffList = Arrays.asList(staff1, staff2);
        
        when(staffService.searchStaffByLastName(lastName)).thenReturn(staffList);
        
        List<Staff> result = staffController.searchStaffByLastName(lastName);
        
        assertThat(result).isEqualTo(staffList);
        verify(staffService, times(1)).searchStaffByLastName(lastName);
    }

    @Test
    public void testSearchStaffByFirstName() {
        String firstName = "John";
        Staff staff1 = new Staff();
        staff1.setStaffId((byte) 1);
        staff1.setFirstName("John");
        staff1.setLastName("Doe");
        Staff staff2 = new Staff();
        staff2.setStaffId((byte) 2);
        staff2.setFirstName("John");
        staff2.setLastName("Smith");
        List<Staff> staffList = Arrays.asList(staff1, staff2);
        
        when(staffService.searchStaffByFirstName(firstName)).thenReturn(staffList);
        
        List<Staff> result = staffController.searchStaffByFirstName(firstName);
        
        assertThat(result).isEqualTo(staffList);
        verify(staffService, times(1)).searchStaffByFirstName(firstName);
    }

    @Test
    public void testSearchStaffByEmail() {
        String email = "john.doe@example.com";
        Staff staff = new Staff();
        staff.setStaffId((byte) 1);
        staff.setFirstName("John");
        staff.setLastName("Doe");
        staff.setEmail(email);
        
        when(staffService.searchStaffByEmail(email)).thenReturn(staff);
        
        Staff result = staffController.searchStaffByEmail(email);
        
        assertThat(result).isEqualTo(staff);
        verify(staffService, times(1)).searchStaffByEmail(email);
    }


    @Test
    public void testSearchStaffByCity() {
        String city = "New York";
        Staff staff1 = new Staff();
        staff1.setStaffId((byte) 1);
        staff1.setFirstName("John");
        staff1.setLastName("Doe");
        Staff staff2 = new Staff();
        staff2.setStaffId((byte) 2);
        staff2.setFirstName("Jane");
        staff2.setLastName("Smith");
        List<Staff> staffList = Arrays.asList(staff1, staff2);
        
        when(staffService.searchStaffByCity(city)).thenReturn(staffList);
        
        List<Staff> result = staffController.searchStaffByCity(city);
        
        assertThat(result).isEqualTo(staffList);
        verify(staffService, times(1)).searchStaffByCity(city);
    }
}

