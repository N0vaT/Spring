package edu.school21.service.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
@PropertySource("classpath:db.properties")
public class ApplicationConfig {

    @Bean
    public HikariConfig hikariConfig(Environment env){
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setMaximumPoolSize(10);
        hikariConfig.setDataSourceClassName("org.postgresql.ds.PGSimpleDataSource");
        hikariConfig.setMaxLifetime(3000);
        hikariConfig.setIdleTimeout(3000);
        hikariConfig.setJdbcUrl(env.getProperty("db.url"));
        hikariConfig.setUsername(env.getProperty("db.user"));
        hikariConfig.setPassword(env.getProperty("db.password"));
        return hikariConfig;
    }
    @Bean
    public HikariDataSource hikariDataSource(HikariConfig hikariConfig){
        return new HikariDataSource(hikariConfig);
    }
    @Bean
    public DataSource dataSource(HikariDataSource hikariDataSource){
        return hikariDataSource;
    }

    @Bean
    public DriverManagerDataSource driverManagerDataSource(Environment env){
        return new DriverManagerDataSource(env.getProperty("db.url"), env.getProperty("db.user"), env.getProperty("db.password"));
    }
    @Bean
    public JdbcTemplate jdbcTemplate(DriverManagerDataSource driverManagerDataSource){
        return new JdbcTemplate(driverManagerDataSource);
    }
}
