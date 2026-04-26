package com.example.back_healthy_food_app.auth;

import com.example.back_healthy_food_app.auth.dto.AuthResponse;
import com.example.back_healthy_food_app.auth.dto.SigninRequest;
import com.example.back_healthy_food_app.auth.dto.SignupRequest;
import com.example.back_healthy_food_app.user.UserDetailsImpl;
import com.example.back_healthy_food_app.user.UserEntity;
import com.example.back_healthy_food_app.user.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AuthService {

    private final BCryptPasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final JwtCore jwtCore;

    public AuthService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtCore jwtCore) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtCore = jwtCore;
    }

    public AuthResponse signup(SignupRequest request) {
        if(userRepository.existsByEmail(request.getEmail())){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email is already in use");
        }
        UserEntity userEntity = new UserEntity();

        userEntity.setName(request.getName());
        userEntity.setEmail(request.getEmail());
        userEntity.setGender(request.getGender());
        userEntity.setBirthDate(request.getBirthDate());
        String hashed = passwordEncoder.encode(request.getPassword());
        userEntity.setPassword(hashed);

        UserEntity savedUser = userRepository.save(userEntity);

        //авторизация внутри регистрации (косяк или нет хз)
        UserDetailsImpl userDetails = UserDetailsImpl.build(savedUser);
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                userDetails,
                null,
                userDetails.getAuthorities()
        );
        String jwt = jwtCore.generateJwtToken(authentication);
        //

        return new AuthResponse(savedUser,jwt);
    }

    public AuthResponse signin(SigninRequest request) {
        Authentication authentication = null;
        try{
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    request.getEmail(), request.getPassword()
            ));
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid username or password");
        }
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtCore.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        UserEntity user = userRepository.findByEmail(userDetails.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        return new AuthResponse(user,jwt);
    }
}
