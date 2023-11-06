package edu.school21.service.services;

import org.springframework.stereotype.Service;

@Service
public interface UserService {
    String signUp(String email);
}
