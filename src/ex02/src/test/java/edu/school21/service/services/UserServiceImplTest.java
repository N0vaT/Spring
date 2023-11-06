package edu.school21.service.services;

import edu.school21.service.config.TestApplicatoinConfig;
import edu.school21.service.exceptions.UserWithEmailAlreadyExistsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceImplTest {
    private UserService userService;
    private final String EXPECTED_PASSWORD = "password";
    @BeforeEach
    void init(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(TestApplicatoinConfig.class);
        userService = context.getBean(UserService.class);
    }

    @ParameterizedTest(name = "email - {0} not busy")
    @ValueSource(strings = {"123456", "3214", "new", "12345124"})
    void signUpCorrect(String argument){
        assertEquals(EXPECTED_PASSWORD, userService.signUp(argument));
    }

    @ParameterizedTest(name = "email - {0} not busy")
    @ValueSource(strings = {"Nova", "Max", "Nikita", "Misha"})
    void signUpException(String argument){
        assertThrows(UserWithEmailAlreadyExistsException.class, () -> userService.signUp(argument));
    }
}