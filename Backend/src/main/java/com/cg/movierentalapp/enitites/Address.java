package com.cg.movierentalapp.enitites;

import java.sql.Timestamp;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private Short addressId;

    @Column(name = "address", nullable = false)
    @NotEmpty(message = "Address is required")
    private String address;

    @Column(name = "address2")
    private String address2;

    @Column(name = "district", nullable = false)
    @NotEmpty(message = "District is required")
    private String district;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "city_id")
    @NotNull(message = "City is required")
    private City city;

    @Column(name = "postal_code")
    @NotEmpty(message = "Postal code is required")
    @Size(max = 10, message = "Postal code cannot exceed 10 characters")
    private String postalCode;

    @Column(name = "phone", nullable = false)
    @NotEmpty(message = "Phone number is required")
    @Size(max = 20, message = "Phone number cannot exceed 20 characters")
    private String phone;

    @Column(name = "last_update", nullable = false)
    private Timestamp lastUpdate;

    public Address(Short addressId, String address, String address2, String district, City city, String postalCode,
            String phone, Timestamp lastUpdate, List<Staff> staffs) {
        super();
        this.addressId = addressId;
        this.address = address;
        this.address2 = address2;
        this.district = district;
        this.city = city;
        this.postalCode = postalCode;
        this.phone = phone;
        this.lastUpdate = lastUpdate;
        // this.staffs = staffs;
    }

    public Address() {
        super();
        // TODO Auto-generated constructor stub
    }

    public Short getAddressId() {
        return addressId;
    }

    public void setAddressId(Short addressId) {
        this.addressId = addressId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    @Override
    public String toString() {
        return "Address [addressId=" + addressId + ", address=" + address + ", address2=" + address2 + ", district="
                + district + ", city=" + city + ", postalCode=" + postalCode + ", phone=" + phone + ", lastUpdate="
                + lastUpdate + "]";
    }
}
