package com.shaista.shipping.services;

import com.shaista.shipping.domain.ShippingRates;
import com.shaista.shipping.repositories.ShippingRepository;

import java.util.List;

public class ShippingService {

    private ShippingRepository repository;

    public ShippingService(ShippingRepository repository) { this.repository=repository; }

    public ShippingRates getById(long id) {
        return repository.getById(id);
    }
    public List<ShippingRates> getAll(int offset, int limit) {
        return repository.getAll(offset, limit);}

    public List<ShippingRates> findByCountry(String country) {
        return repository.findByCountry(country);
    }


    public void delete(ShippingRates shippingRates) {
        repository.delete(shippingRates.getShippingRateID());
    }

    public ShippingRates save(ShippingRates shippingRates) {
        ShippingRates savedShipping = repository.save(shippingRates);
        return savedShipping;
    }






}


