package com.example.Role.Based.Task.Controllr;

import com.example.Role.Based.Task.ApiResponse.ApiResponse;
import com.example.Role.Based.Task.DTOClass.UserDTO;
import com.example.Role.Based.Task.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/userapi")
public class UserControll {
    @Autowired
    private ApiResponse apiResponse;
    @Autowired
    private UserService userService;

    @PostMapping(value = "/adduser")
    public  ApiResponse addUser(@RequestBody UserDTO userDTO) throws Exception {
        return userService.addUser(userDTO);
    }
    @PostMapping(value = "/login")
    public  ApiResponse login(@RequestBody UserDTO userDTO) throws Exception {
        return userService.login(userDTO);
    }
}
