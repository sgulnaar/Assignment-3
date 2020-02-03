package com.shaista.shipping.domain;

import java.util.Calendar;

public class ShippingRates {
    private long ShippingRateID;
    private String   Country;
    private long  FlatRate;
    private long   version;

    public ShippingRates(long shippingRateID, String country) {
        ShippingRateID = shippingRateID;
        Country = country;
      //  setShippingRateID(shippingRateID);
    }

    public ShippingRates(long shippingRateID) {
        ShippingRateID = shippingRateID;
    }

    public ShippingRates(String country) {
        Country = country;
    }

    public long getShippingRateID() {
        return ShippingRateID;
    }

    public void setShippingRateID(long shippingRateID) {
        ShippingRateID = shippingRateID;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        if (country == null ) {
            throw new IllegalArgumentException("country is required.");
        }
        String cleanName = country.trim();
        if (cleanName.length() == 0 || cleanName.length() > 50) {
            throw new IllegalArgumentException("country must be between 1 and 50 characters.");
        }
        this.Country = cleanName;
    }

    public long getFlatRate() {
        return FlatRate;
    }

    public void setFlatRate(long flatRate) {
        FlatRate = flatRate;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        if (version == 0)
            throw new IllegalArgumentException("Version cannot be zero.");

        if (version < this.version)
            throw new IllegalArgumentException("Version cannot be older than the current version.");

        this.version = version;
    }

}
