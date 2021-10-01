package com.restaurant.global;

import com.restaurant.model.Meal;

import java.util.ArrayList;
import java.util.List;

public class GlobalData {
    public static List<Meal> cart;
    static {
        cart = new ArrayList<Meal>();
    }
}
