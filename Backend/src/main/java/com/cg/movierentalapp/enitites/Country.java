package com.cg.movierentalapp.enitites;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "country")
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "country_id")
    private Short countryId;

    @Column(name = "country", nullable = false)
    @NotEmpty(message = "Country name is required")
    @Size(max = 50, message = "Country name cannot exceed 50 characters")
    private String country;

    @Column(name = "last_update", nullable = false)
    @NotNull(message = "Last update timestamp is required")
    private Timestamp lastUpdate;

    public Country(Short countryId, String country, Timestamp lastUpdate) {
        super();
        this.countryId = countryId;
        this.country = country;
        this.lastUpdate = lastUpdate;
    }

    public Country() {
        super();
    }

    public Short getCountryId() {
        return countryId;
    }

    public void setCountryId(Short countryId) {
        this.countryId = countryId;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    @Override
    public String toString() {
        return "Country [countryId=" + countryId + ", country=" + country + ", lastUpdate=" + lastUpdate + "]";
    }
}
