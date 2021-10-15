package com.restaurant.service;

import com.restaurant.model.Promo;
import com.restaurant.repository.PromoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PromoService {

    @Autowired
    PromoRepository promoRepository;

    public List<Promo> getAllPromo() {
        return promoRepository.findAll();
    }

    public Promo findPromoByCode(int code){ return promoRepository.findPromoByCode(code);}

    public void addPromo(Promo promo) {
        promoRepository.save(promo);
    }

    public void removePromoById(int id) {
        promoRepository.deleteById(id);
    }

    public Optional<Promo> retrievePromoByID(int id) {
        return promoRepository.findById(id);
    }
}
