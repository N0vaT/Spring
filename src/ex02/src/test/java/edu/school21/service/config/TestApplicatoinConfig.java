package edu.school21.service.config;

import edu.school21.service.repositories.UserRepositoryJdbcImpl;
import edu.school21.service.repositories.UsersRepository;
import edu.school21.service.services.UserService;
import edu.school21.service.services.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;

import javax.sql.DataSource;

@Configuration
public class TestApplicatoinConfig {

    @Bean
    public DataSource dataSource(){
        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        return builder.generateUniqueName(true)
                .addScripts("classpath:schema.sql", "classpath:data.sql")
                .build();
    }
    @Bean
    public UsersRepository usersRepository(DataSource dataSource){
        return new UserRepositoryJdbcImpl(dataSource);
    }
    @Bean
    public UserService userService(UsersRepository usersRepository){
        return new UserServiceImpl(usersRepository);
    }
}
