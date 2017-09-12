package com.example.ramdan.myapplication.data;

/**
 * Created by ramdan on 11/09/17.
 */

public interface IMenu {

    public void setName(String name);
    public void setPhoto(String photo);
    public void setPrice(String price);
    public void setDescription(String description);

    public String getName();
    public String getPhoto();
    public String getPrice();
    public String getDescription();
}
