package com.restaurant.global;

import com.restaurant.model.Meal;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

public class GlobalData {
    public static List<Meal> cart;
    public static double totalCost;
    public static Double costDeducted;
    public static Double costAfterPromo;
    static {
        cart = new ArrayList<Meal>();
    }
}
