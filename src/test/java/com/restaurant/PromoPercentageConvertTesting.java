package com.restaurant;

import com.restaurant.controller.CartController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class PromoPercentageConvertTesting {

    CartController cartController;

    @Test
    final void testConvertPercentageToDecimal(){
        cartController = new CartController();
        double test = 20;
        double test1 = cartController.convertPercentageToDecimal(test);
        Assertions.assertEquals(0.8,test1);
    }

    @Test
    final void testConvertPercentageToDecimal2(){
        cartController = new CartController();
        double test = 20;
        double test1 = cartController.convertPercentageToDecimal(test);
        Assertions.assertNotEquals(0.7,test1);
    }
}
