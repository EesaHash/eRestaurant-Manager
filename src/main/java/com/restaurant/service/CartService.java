package com.restaurant.service;

import com.restaurant.model.CartItem;
import com.restaurant.model.Person;
import com.restaurant.repository.CartItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

    @Autowired
    private CartItemRepository cartItemRepository;

    public List<CartItem> listCartItem(Person person){
        return cartItemRepository.findByPerson(person);
    }
}
