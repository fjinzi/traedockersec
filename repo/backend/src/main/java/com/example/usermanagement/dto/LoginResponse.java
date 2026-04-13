package com.example.usermanagement.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "登录响应对象")
public class LoginResponse {
    @Schema(description = "是否成功", example = "true")
    private boolean success;
    
    @Schema(description = "响应消息", example = "登录成功")
    private String message;
    
    @Schema(description = "用户信息")
    private UserDTO user;

    public LoginResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public LoginResponse(boolean success, String message, UserDTO user) {
        this.success = success;
        this.message = message;
        this.user = user;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }
}
