package com.example.usermanagement.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "用户信息对象")
public class UserDTO {
    @Schema(description = "用户 ID", example = "1")
    private Long id;
    
    @Schema(description = "用户名", example = "admin")
    private String username;
    
    @Schema(description = "邮箱", example = "admin@example.com")
    private String email;
    
    @Schema(description = "角色", example = "administrator")
    private String role;
    
    @Schema(description = "状态", example = "active")
    private String status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
