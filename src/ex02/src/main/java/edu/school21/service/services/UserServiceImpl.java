package edu.school21.service.services;

import edu.school21.service.exceptions.UserNotFoundException;
import edu.school21.service.exceptions.UserWithEmailAlreadyExistsException;
import edu.school21.service.models.User;
import edu.school21.service.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;
import java.util.Random;


public class UserServiceImpl implements UserService{
    private final UsersRepository usersRepository;

    @Autowired
    public UserServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public String signUp(String email) {
        Optional user = usersRepository.findByEmail(email);
        if(user.isPresent()){
            throw new UserWithEmailAlreadyExistsException("A user with the " + email + " already exists");
        }
        User newUser = new User();
        newUser.setEmail(email);
        String pass = generatePass();
        newUser.setPassword(pass);
        usersRepository.save(newUser);
        return pass;
    }
    private String generatePass(){
//        Random random = new Random();
//        int value = 1000 + random.nextInt(9999);
//        return Integer.toString(value);
        return "password";
    }
}
