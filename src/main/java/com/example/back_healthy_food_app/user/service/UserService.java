package com.example.back_healthy_food_app.user.service;

import com.example.back_healthy_food_app.errors.EmailAlreadyExistsException;
import com.example.back_healthy_food_app.errors.EmailNotFoundException;
import com.example.back_healthy_food_app.errors.UserNotFoundException;
import com.example.back_healthy_food_app.user.UserDetailsImpl;
import com.example.back_healthy_food_app.user.dto.UpdateDtoUser;
import com.example.back_healthy_food_app.user.dto.UserResponse;
import com.example.back_healthy_food_app.user.storage.UserEntity;
import com.example.back_healthy_food_app.user.storage.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService, IUserService {

    private final UserRepository repository;
    private final BCryptPasswordEncoder encoder;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder encoder) {
        this.repository = userRepository;
        this.encoder = encoder;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws EmailNotFoundException {
        UserEntity user = repository.findByEmail(email)
                .orElseThrow(() -> new EmailNotFoundException(email));
        return UserDetailsImpl.build(user);
    }


    @Override
    public UserResponse getById(String id) {
       return repository.findById(id).map(UserEntity::asUserResponse)
                .orElseThrow(() -> new UserNotFoundException(id));
    }


    @Override
    public UserEntity getEntityById(String id) {
        return repository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    @Override
    public void deleteById(String id) {
        if(!repository.existsById(id)){
            throw new UserNotFoundException(id);
        }
        repository.deleteById(id);
    }

    @Override
    public UserResponse update(UpdateDtoUser dto,String id) {
        UserEntity user = repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        if(dto.getName() != null) {user.setName(dto.getName());}
        if(dto.getEmail() != null) {
            repository.findByEmail(dto.getEmail())
                    .ifPresent(existing -> {
                        if(!existing.getId().equals(id)) {
                            throw new EmailAlreadyExistsException();
                        }
                    });
            user.setEmail(dto.getEmail());
        }
        if(dto.getPassword() != null) {user.setPassword(encoder.encode(dto.getPassword()));}
        if(dto.getGender() != null) {user.setGender(dto.getGender());}
        if(dto.getBirthDate() != null) {user.setBirthDate(dto.getBirthDate());}
        return repository.save(user).asUserResponse();
    }
}
