package com.shaista.shipping.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Link {

    @JsonProperty
    private String href;
    @JsonProperty
    private String rel;
    @JsonProperty
    private String type;

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getRel() {
        return rel;
    }

    public void setRel(String rel) {
        this.rel = rel;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
