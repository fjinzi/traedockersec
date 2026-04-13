package com.example.usermanagement.service.impl;

import com.example.usermanagement.dto.LoginRequest;
import com.example.usermanagement.dto.LoginResponse;
import com.example.usermanagement.dto.UserDTO;
import com.example.usermanagement.entity.User;
import com.example.usermanagement.mapper.UserMapper;
import com.example.usermanagement.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    
    private final UserMapper userMapper;
    
    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }
    
    @Override
    public LoginResponse login(LoginRequest request) {
        User user = userMapper.findByUsername(request.getUsername());
        
        if (user == null) {
            return new LoginResponse(false, "用户不存在");
        }
        
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
