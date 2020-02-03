package com.shaista.shipping.repositories.Impl;

import com.shaista.shipping.domain.ShippingRates;
import com.shaista.shipping.repositories.ShippingRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class InMemoryShippingRepository implements ShippingRepository {

    private Map<Long, ShippingRates> data = new HashMap<Long, ShippingRates>();

    @Override
    public List<ShippingRates> findByCountry(String country) {
        return data.values().stream()
                .filter(s->s.getCountry().startsWith(country))
                .collect(Collectors.toList());
    }

    @Override
    public ShippingRates getById(long id) {
        return data.get(id);
    }

    @Override
    public List<ShippingRates> getAll(int offSet, int limit) {
        return data.values().stream()
                .skip(offSet)
                .limit(limit)
                .collect(Collectors.toList());
    }

    @Override
    public ShippingRates save(ShippingRates entity) {
        if (entity.getShippingRateID() == 0) {
            long id = data.keySet().stream()
                    .max(Long::compare)
                    .orElse(1L);
            entity.setShippingRateID(id);
        }
        entity.setVersion(entity.getVersion() + 1);
        data.put(entity.getShippingRateID(), entity);
        return entity;
    }

    @Override
    public void delete(long id) {

    }
}
