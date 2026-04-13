package com.example.usermanagement.service.impl;

import com.example.usermanagement.dto.LoginRequest;
import com.example.usermanagement.dto.LoginResponse;
import com.example.usermanagement.dto.UserDTO;
import com.example.usermanagement.entity.User;
import com.example.usermanagement.repository.UserRepository;
import com.example.usermanagement.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public LoginResponse login(LoginRequest request) {
        Optional<User> userOpt = userRepository.findByUsername(request.getUsername());

        if (userOpt.isEmpty()) {
            return new LoginResponse(false, "用户不存在");
        }

        User user = userOpt.get();

        if (!"active".equals(user.getStatus())) {
            return new LoginResponse(false, "用户账号已被禁用");
        }

        if (!user.getPassword().equals(request.getPassword())) {
            return new LoginResponse(false, "密码错误");
        }

        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(user, userDTO);

        return new LoginResponse(true, "登录成功", userDTO);
    }
}
