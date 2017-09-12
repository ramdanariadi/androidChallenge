package com.example.ramdan.myapplication.data;

/**
 * Created by ramdan on 11/09/17.
 */

public class RestaurantMenus implements IMenu {
    String name;
    String photo;
    String price;
    String description;

    public RestaurantMenus(String name, String photo, String price, String description) {
        this.name = name;
        this.photo = photo;
        this.price = price;
        this.description = description;
    }

    public RestaurantMenus(String name, String price, String description) {
        this.name = name;
        this.price = price;
        this.description = description;
    }


    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Override
    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getPhoto() {
        return photo;
    }

    @Override
    public String getPrice() {
        return price;
    }

    @Override
    public String getDescription() {
        return description;
    }
}
