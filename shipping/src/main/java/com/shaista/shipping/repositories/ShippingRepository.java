package com.shaista.shipping.repositories;

import com.shaista.shipping.domain.ShippingRates;

import java.util.List;

public interface ShippingRepository extends Repository<ShippingRates>{

    List<ShippingRates> findByCountry(String country);
}
