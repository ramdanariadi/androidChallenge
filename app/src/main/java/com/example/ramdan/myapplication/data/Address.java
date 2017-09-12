package com.example.ramdan.myapplication.data;

/**
 * Created by ramdan on 11/09/17.
 */

public class Address {
    String street;
    String lat;
    String lng;

    public Address(String street) {
        this.street = street;
    }

    public Address(String street, String lat, String lng) {
        this.street = street;
        this.lat = lat;
        this.lng = lng;
    }

    public String getStreet() {
        return street;
    }

    public String getLat() {
        return lat;
    }

    public String getLng() {
        return lng;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }
}
