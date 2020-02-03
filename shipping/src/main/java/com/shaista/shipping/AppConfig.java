package com.shaista.shipping;

import com.shaista.shipping.repositories.Impl.MySqlShippingRepository;
import com.shaista.shipping.repositories.Impl.ShippingRowMapper;
import com.shaista.shipping.repositories.ShippingRepository;
import com.shaista.shipping.services.ShippingService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.sql.DataSource;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.shaista.shipping"})
public class AppConfig {

        @Bean
        public DataSource datasource() {
            DriverManagerDataSource dataSource = new DriverManagerDataSource();
            dataSource.setDriverClassName("com.mysql.jdbc.Driver");
            dataSource.setUrl("jdbc:mysql://localhost:3306/shipping-db");
            //dataSource.setUsername("customers-user");
            dataSource.setUsername("root");
            dataSource.setPassword("password");
            return dataSource;
        }
        //DI Configuration goes here.
        @Bean
        public ShippingService shippingService(ShippingRepository shippingRepository) {
            return new ShippingService(shippingRepository);
        }

        @Bean
        public ShippingRepository shippingRepository(DataSource dataSource,
                                                     ShippingRowMapper shippingRowMapper
                                                    ) {
            return new MySqlShippingRepository(dataSource, shippingRowMapper);
        }

        @Bean
        public ShippingRowMapper shippingRowMapper() {
            return new ShippingRowMapper();
        }

}
