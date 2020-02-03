package com.shaista.shipping.api;

import com.shaista.shipping.domain.ShippingRates;

public class ShippingMapper {
    private ShippingMapper(){}

    public static ShippingModel toModel(ShippingRates entity) {
        ShippingModel model = new ShippingModel();
        model.setShippingRateID(entity.getShippingRateID());
        model.setCountry(entity.getCountry());
        model.setFlatRate(entity.getFlatRate());
        model.setVersion(entity.getVersion());

//        Link addressLink = new Link();
//        addressLink.setHref(String.format("/shipping/%d/addresses", entity.getShippingRateID()));
//        model.getLinks().add(addressLink);
        return model;
    }

    public static ShippingRates toEntity(ShippingModel model) {
        ShippingRates entity = new ShippingRates(model.getCountry());
        entity.setShippingRateID(model.getShippingRateID());
        entity.setCountry(model.getCountry());
        entity.setFlatRate(model.getFlatRate());
        return entity;
    }

    public static ShippingRates toEntity(ShippingModel model, ShippingRates entity) {
        entity.setCountry(model.getCountry());
        entity.setFlatRate(model.getFlatRate());
        return entity;
    }


}
