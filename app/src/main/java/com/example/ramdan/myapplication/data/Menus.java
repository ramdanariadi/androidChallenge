package com.example.ramdan.myapplication.data;

import java.util.List;

/**
 * Created by ramdan on 11/09/17.
 */

public class Menus {

    List<IMenu> foods;
    List<IMenu> drinks;
    List<IMenu> appetizer;

    public List<IMenu> getFoods() {
        return foods;
    }

    public List<IMenu> getDrinks() {
        return drinks;
    }

    public List<IMenu> getAppetizer() {
        return appetizer;
    }

    public void setFoods(List<IMenu> foods) {
        this.foods = foods;
    }

    public void setDrinks(List<IMenu> drinks) {
        this.drinks = drinks;
    }

    public void setAppetizer(List<IMenu> appetizer) {
        this.appetizer = appetizer;
    }
}
