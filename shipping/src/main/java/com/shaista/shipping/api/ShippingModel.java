package com.shaista.shipping.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.ArrayList;
import java.util.List;

@JsonSerialize
public class ShippingModel {
    @JsonProperty
    private long shippingRateID;
    @JsonProperty
    private String country;
    @JsonProperty
    private long flatRate;
    @JsonProperty
    private long version;
    @JsonProperty
    private List<Link> links = new ArrayList<>();

    public long getShippingRateID() {
        return shippingRateID;
    }

    public void setShippingRateID(long shippingRateID) {
        this.shippingRateID = shippingRateID;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public long getFlatRate() {
        return flatRate;
    }

    public void setFlatRate(long flatRate) {
        this.flatRate = flatRate;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }
}
