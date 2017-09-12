package com.example.ramdan.myapplication.data;

/**
 * Created by ramdan on 11/09/17.
 */

public class Restaurant {
    String id;
    String photo;
    String name;
    int rate;
    Menus menus;
    Address restaurantAddress;

    public Restaurant(String id, String name, int rate, Menus menus, Address restaurantAddress) {
        this.id = id;
        this.name = name;
        this.rate = rate;
        this.menus = menus;
        this.restaurantAddress = restaurantAddress;
    }

    public Restaurant(String id, String photo, String name, int rate) {
        this.id = id;
        this.photo = photo;
        this.name = name;
        this.rate = rate;
    }

    public Restaurant(String id, String photo, String name, int rate, Menus menus, Address restaurantAddress) {
        this.id = id;
        this.photo = photo;
        this.name = name;
        this.rate = rate;
        this.menus = menus;
        this.restaurantAddress = restaurantAddress;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public void setMenus(Menus menus) {
        this.menus = menus;
    }

    public void setRestaurantAddress(Address restaurantAddress) {
        this.restaurantAddress = restaurantAddress;
    }

    public String getId() {
        return id;
    }

    public String getPhoto() {
        return photo;
    }

    public String getName() {
        return name;
    }

    public int getRate() {
        return rate;
    }

    public Menus getMenus() {
        return menus;
    }

    public Address getRestaurantAddress() {
        return restaurantAddress;
    }
}
