package com.example.usermanagement;

import com.example.usermanagement.dto.LoginRequest;
import com.example.usermanagement.dto.LoginResponse;
import com.example.usermanagement.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTest {
    
    @Autowired
    private UserService userService;
    
    @Test
    public void testLoginSuccess() {
        LoginRequest request = new LoginRequest();
        request.setUsername("admin");
        request.setPassword("123456");
        
        LoginResponse response = userService.login(request);
        
        assertTrue(response.isSuccess());
        assertEquals("登录成功", response.getMessage());
        assertNotNull(response.getUser());
        assertEquals("admin", response.getUser().getUsername());
    }
    
    @Test
    public void testLoginUserNotFound() {
        LoginRequest request = new LoginRequest();
        request.setUsername("nonexistent");
        request.setPassword("123456");
        
        LoginResponse response = userService.login(request);
        
        assertFalse(response.isSuccess());
        assertEquals("用户不存在", response.getMessage());
    }
    
    @Test
    public void testLoginWrongPassword() {
        LoginRequest request = new LoginRequest();
        request.setUsername("admin");
        request.setPassword("wrongpassword");
        
        LoginResponse response = userService.login(request);
        
        assertFalse(response.isSuccess());
        assertEquals("密码错误", response.getMessage());
    }
    
    @Test
    public void testLoginInactiveUser() {
        LoginRequest request = new LoginRequest();
        request.setUsername("wangwu");
        request.setPassword("123456");
        
        LoginResponse response = userService.login(request);
        
        assertFalse(response.isSuccess());
        assertEquals("用户账号已被禁用", response.getMessage());
    }
}
