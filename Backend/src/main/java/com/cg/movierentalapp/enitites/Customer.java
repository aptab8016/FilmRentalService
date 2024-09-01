package com.cg.movierentalapp.enitites;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Short customerId;

    @Column(name = "store_id", nullable = false)
    @NotNull(message = "Store ID is required")
    private Byte storeId;

    @Column(name = "first_name", nullable = false)
    @NotEmpty(message = "First name is required")
    @Size(max = 50, message = "First name cannot exceed 50 characters")
    private String firstName;

    @Column(name = "last_name", nullable = false)
    @NotEmpty(message = "Last name is required")
    @Size(max = 50, message = "Last name cannot exceed 50 characters")
    private String lastName;

    @Column(name = "email")
    @Email(message = "Invalid email format")
    @Size(max = 100, message = "Email cannot exceed 100 characters")
    private String email;

    @ManyToOne
    @JoinColumn(name = "address_id")
    @NotNull(message = "Address is required")
    private Address address;

    @Column(name = "active", nullable = false)
    private int active;

    @Column(name = "create_date", nullable = false)
    @NotNull(message = "Create date is required")
    private Timestamp createDate;

    @Column(name = "last_update", nullable = false)
    @NotNull(message = "Last update timestamp is required")
    private Timestamp lastUpdate;

    public Customer(Short customerId, Byte storeId, String firstName, String lastName, String email, Address address,
            int active, Timestamp createDate, Timestamp lastUpdate) {
        super();
        this.customerId = customerId;
        this.storeId = storeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.active = active;
        this.createDate = createDate;
        this.lastUpdate = lastUpdate;
    }

    public Customer() {
        super();
    }

    public Short getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Short customerId) {
        this.customerId = customerId;
    }

    public Byte getStoreId() {
        return storeId;
    }

    public void setStoreId(Byte storeId) {
        this.storeId = storeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    @Override
    public String toString() {
        return "Customer [customerId=" + customerId + ", storeId=" + storeId + ", firstName=" + firstName
                + ", lastName=" + lastName + ", email=" + email + ", address=" + address + ", active=" + active
                + ", createDate=" + createDate + ", lastUpdate=" + lastUpdate + "]";
    }

    // Getters and setters
}
