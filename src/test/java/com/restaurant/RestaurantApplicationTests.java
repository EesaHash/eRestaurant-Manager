package com.restaurant;

import com.restaurant.model.Promo;
import com.restaurant.repository.PromoRepository;
import com.restaurant.service.PromoService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@SpringBootTest
class RestaurantApplicationTests {

	@Mock
	PromoRepository promoRepository;

	@InjectMocks
	PromoService promoService;

	@Test
	final void testFindPromoByCode() {
		Promo promo = new Promo();
		promo.setCode(1200);
		promo.setId(11);
		promo.setPercentage(0.8);
		promo.setDescription("20 percent discount");

		when(promoRepository.findPromoByCode(anyInt())).thenReturn(promo);

		Promo p = promoService.findPromoByCode(1200);
		assertNotNull(p);
		assertEquals(1200, p.getCode());
	}

	@Test
	final void testGetAllPromo() {
		Promo promo1 = new Promo(11, 1211, 0.8, "20 percentdiscount");
		Promo promo2 = new Promo(12, 1212, 0.9, "10 percentdiscount");
		List<Promo> listOfPromo = new LinkedList<Promo>();
		listOfPromo.add(promo1);
		listOfPromo.add(promo2);

		when(promoRepository.findAll()).thenReturn(listOfPromo);
		List<Promo> listOfPromo2 = promoService.getAllPromo();

		assertNotNull(listOfPromo2);
	}

	@Test
	final void testAddPromo() {
		Promo promo1 = new Promo(11, 1211, 0.8, "20 percentdiscount");
		promoRepository.save(promo1);
		when(promoRepository.findPromoByCode(anyInt())).thenReturn(promo1);
		Promo p = promoService.findPromoByCode(1211);

		assertEquals(1211, p.getCode());
	}

	@Test
	final void testRemovePromoByIdPromo() {
		Promo promo1 = new Promo(11, 1211, 0.8, "20 percentdiscount");
		promoRepository.deleteById(11);
		when(promoRepository.findPromoByCode(anyInt())).thenReturn(null);
		Promo p = promoService.findPromoByCode(1211);

		assertNull(p);
	}

}
