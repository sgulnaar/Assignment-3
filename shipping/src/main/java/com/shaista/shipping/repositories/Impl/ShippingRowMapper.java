package com.shaista.shipping.repositories.Impl;

import com.shaista.shipping.domain.ShippingRates;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class ShippingRowMapper implements RowMapper<ShippingRates> {
    @Override
    public ShippingRates mapRow(ResultSet rs, int rowNum) throws SQLException {
    ShippingRates shippingRates = new ShippingRates(rs.getString("Country"));
    shippingRates.setShippingRateID(rs.getLong("ShippingRateID"));
    shippingRates.setFlatRate(rs.getLong("FlatRate"));
    shippingRates.setVersion(rs.getLong("Version"));
    return shippingRates;

}


}
