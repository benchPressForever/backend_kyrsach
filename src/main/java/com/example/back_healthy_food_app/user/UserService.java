package com.example.back_healthy_food_app.user;

import com.example.back_healthy_food_app.errors.EmailNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    private UserRepository repository;

    public UserService(UserRepository userRepository) {
        this.repository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws EmailNotFoundException {
        UserEntity user = repository.findByEmail(email)
                .orElseThrow(() -> new EmailNotFoundException(email));
        return UserDetailsImpl.build(user);
    }
}
