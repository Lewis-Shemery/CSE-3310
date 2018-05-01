package com.toreytaylor.utacaterering.model.Objects;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;


public class AdditionalInfo {
    @Ignore
    public int utaId;

    @Ignore
    public String address;

    @Ignore
    public String city;

    @Ignore
    public String state;

    @Ignore
    public int zipCode;

    @Ignore
    public String email;


    public void setUtaId(int utaId) { this.utaId = utaId; }

    public int getUtaId() {
        return utaId;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }

    public void setZipCode(int zipCode) { this.zipCode = zipCode; }

    public int getZipCode() {
        return zipCode;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

}
