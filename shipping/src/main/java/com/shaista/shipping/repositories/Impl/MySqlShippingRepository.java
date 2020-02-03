package com.shaista.shipping.repositories.Impl;

import com.shaista.shipping.domain.ShippingRates;
import com.shaista.shipping.repositories.ShippingRepository;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.sql.DataSource;
import java.util.List;
import java.util.Objects;

public class MySqlShippingRepository implements ShippingRepository {

    private DataSource dataSource;
    private ShippingRowMapper shippingRowMapper;

    public MySqlShippingRepository(DataSource dataSource,
                                   ShippingRowMapper shippingRowMapper) {
        this.dataSource = dataSource;
        this.shippingRowMapper = shippingRowMapper;
    }

    @Override
    public List<ShippingRates> findByCountry(String companyName) {
        NamedParameterJdbcTemplate db = new NamedParameterJdbcTemplate(dataSource);
        String sql = "SELECT `ShippingRates`.`ShippingRateID`,\n" +
                "    `ShippingRates`.`Country`,\n" +
                "    `ShippingRates`.`FlatRate`,\n" +
                "    `ShippingRates`.`Version`,\n" +
                "    `ShippingRates`.`ObjectID`\n" +
                "FROM `shipping-db`.`ShippingRates`;\n" +
                "WHERE Country LIKE : country";
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("companyName", companyName + "%");

        List<ShippingRates> shippingRates = db.query(sql, params, shippingRowMapper);
        return shippingRates;

    }

    @Override
    public ShippingRates getById(long id) {
        NamedParameterJdbcTemplate db = new NamedParameterJdbcTemplate(dataSource);
        String sql ="SELECT `ShippingRates`.`ShippingRateID`,\n" +
                "    `ShippingRates`.`Country`,\n" +
                "    `ShippingRates`.`FlatRate`,\n" +
                "    `ShippingRates`.`Version`,\n" +
                "    `ShippingRates`.`ObjectID`\n" +
                "FROM `shipping-db`.`ShippingRates`\n" +
                "WHERE ShippingRateID = :id";
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("id", id);
        ShippingRates shippingRates = db.queryForObject(sql, params, shippingRowMapper);
        return shippingRates;
    }

    @Override
    public List<ShippingRates> getAll(int offSet, int limit) {
        NamedParameterJdbcTemplate db = new NamedParameterJdbcTemplate(dataSource);
        String sql ="SELECT `ShippingRates`.`ShippingRateID`,\n" +
                "    `ShippingRates`.`Country`,\n" +
                "    `ShippingRates`.`FlatRate`,\n" +
                "    `ShippingRates`.`Version`,\n" +
                "    `ShippingRates`.`ObjectID`\n" +
                "FROM `shipping-db`.`ShippingRates`\n" +
                "LIMIT :offset, :limit";
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("offset", offSet)
                .addValue("limit", limit);
        List<ShippingRates> shippingRates = db.query(sql, params, shippingRowMapper);
        return shippingRates;
    }

    @Override
    public ShippingRates save(ShippingRates entity) {
        NamedParameterJdbcTemplate db = new NamedParameterJdbcTemplate(dataSource);
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("shippingRateID", entity.getShippingRateID())
                .addValue("country", entity.getCountry())
                .addValue("flatRate", entity.getFlatRate());

        if (entity.getShippingRateID() == 0) {
            //insert

            String sql =  "INSERT INTO `shipping-db`.ShippingRates (Country, FlatRate) VALUES (:country, :flatRate)";

            KeyHolder keyHolder = new GeneratedKeyHolder();
            db.update(sql, params, keyHolder);
            MapSqlParameterSource idParams = new MapSqlParameterSource();
            return getById(keyHolder.getKey().longValue());

        } else {
            //update
           // String sql = "UPDATE shipping-db.ShippingRates SET ShippingRateID = :shippingRateID, Country = :country, FlatRate` = :flatRate, WHERE ShippingRateID = :shippingRateID and Version = :version";
           // String sql = "UPDATE shipping-db.ShippingRates SET ShippingRates.country, ShippingRates.flatRate, ShippingRates.version WHERE ShippingRateID = :shippingRateID ";

           String sql = "UPDATE `shipping-db`.`ShippingRates`\n" +
                   "SET `ShippingRates`.`Country` = :country,\n" +
                   "`ShippingRates`.`FlatRate` = :flatRate,\n" +
                   "`ShippingRates`.`Version` = Version + 1 \n" +
                   "WHERE `ShippingRateID` = :shippingRateID\n";


            params.addValue("id", entity.getShippingRateID())
                    .addValue("version", entity.getVersion());

            int rowsAffected = db.update(sql, params);
            if (rowsAffected == 0) {
                throw new IllegalStateException("Concurrent modification detected");
            }

            return getById(entity.getShippingRateID());
        }
    }

    @Override
    public void delete(long id) {

        NamedParameterJdbcTemplate db = new NamedParameterJdbcTemplate(dataSource);
        MapSqlParameterSource params = new MapSqlParameterSource().addValue("id", id);
        db.update("delete from ShippingRates where ShippingRateID = :id", params);

    }



}
