package edu.school21.service.application;

import edu.school21.service.repositories.UsersRepository;
import edu.school21.service.services.UserService;
import edu.school21.service.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

public class Main {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("edu.school21.service");
        UsersRepository usersRepository1 = context.getBean("userRepositoryJdbcImpl", UsersRepository.class);
        System.out.println(usersRepository1.findAll());
        UsersRepository usersRepository2 = context.getBean("usersRepositoryJdbcTemplateImpl", UsersRepository.class);
        System.out.println(usersRepository2.findAll());
        UserService userService1 = new UserServiceImpl(usersRepository1);
        System.out.println(userService1.signUp("12345"));
        UserService userService2 = new UserServiceImpl(usersRepository2);
        System.out.println(userService2.signUp("54321"));

    }
}
