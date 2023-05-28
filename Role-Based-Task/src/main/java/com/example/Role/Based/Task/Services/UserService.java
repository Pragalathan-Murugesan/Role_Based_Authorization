package com.example.Role.Based.Task.Services;

import com.example.Role.Based.Task.ApiResponse.ApiResponse;
import com.example.Role.Based.Task.DTOClass.UserDTO;
import com.example.Role.Based.Task.Entity.Admin;
import com.example.Role.Based.Task.Entity.User;
import com.example.Role.Based.Task.Implementation.UserImple;
import com.example.Role.Based.Task.JWTTokenUser.TokenGenerationForUser;
import com.example.Role.Based.Task.Repositery.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class UserService implements UserImple {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ApiResponse apiResponse;
    @Autowired
            private TokenGenerationForUser tokenGenerationForUser;
  BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    @Override
    public ApiResponse addUser(UserDTO userDTO) throws Exception {
        try {
            User user = new User();
         String  bcryptPassword =bCryptPasswordEncoder.encode(userDTO.getPassword());
            user.setUserName(userDTO.getUserName());
            user.setEmailID(userDTO.getEmailID());
            user.setPassword(bcryptPassword);
            userRepo.save(user);
            apiResponse.setMessage("User Added Successfully ");
            apiResponse.setStatus(HttpStatus.OK.value());
            apiResponse.setData(user);
        }catch (Exception e){
            throw  new Exception(e.getMessage());
        }
        return apiResponse;
    }

    @Override
    public ApiResponse login(UserDTO userDTO) throws Exception {
        try {
            User user = userRepo.loginApi(userDTO.getEmailID());
            if (user == null) {
                apiResponse.setMessage("Login Failed");
                apiResponse.setData(null);
                apiResponse.setStatus(HttpStatus.BAD_REQUEST.value());
                return apiResponse;
            }
            if (bCryptPasswordEncoder.matches(userDTO.getPassword(), user.getPassword())) {
                String token = tokenGenerationForUser.generateTokens(user);
                HashMap<String, Object> data = new HashMap<>();
                data.put("token", token);
                apiResponse.setMessage("Login Successfully");
                apiResponse.setData(data);
                apiResponse.setStatus(HttpStatus.OK.value());
                return apiResponse;
            }
        }catch (Exception e){
            throw  new Exception(e.getMessage());
        }
        return null;
    }
}
