package com.example.race.config;

import org.hibernate.dialect.Oracle12cDialect;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class JpaConfiguration {
    @Value("${db.username}")
    private String username;

    @Value("${db.password}")
    private String password;

    @Bean
    public DataSource getDataSource() {
        DataSourceBuilder builder = DataSourceBuilder.create();

        builder.url("jdbc:oracle:thin:@localhost:1521:xe");
        builder.username(username);
        builder.password(password);
        System.out.println("APP INFO: the custom datasource bean has been initialized...");

        return builder.build();
    }

    public Map<String, Object> jpaProperties() {
        Map<String, Object> props = new HashMap<>();
        props.put("hibernate.dialect", Oracle12cDialect.class.getName());
        return props;
    }
}
