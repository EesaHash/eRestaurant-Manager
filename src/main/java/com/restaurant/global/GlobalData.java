package com.restaurant.global;

import com.restaurant.model.Meal;
import com.restaurant.model.Promo;

import java.util.ArrayList;
import java.util.List;

public class GlobalData {
    public static List<Meal> cart;
    public static double totalCost;
    public static Promo promo;
    public static Double costDeducted;
    public static Double costAfterPromo;
    static {
        promo = null;
        cart = new ArrayList<Meal>();
        costDeducted = 0.0;
        costAfterPromo = 0.0;
    }
}
