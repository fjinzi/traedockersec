package com.example.usermanagement.controller;

import com.example.usermanagement.dto.LoginRequest;
import com.example.usermanagement.dto.LoginResponse;
import com.example.usermanagement.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
@Tag(name = "认证管理", description = "用户登录认证相关接口")
public class AuthController {
    
    private final UserService userService;
    
    public AuthController(UserService userService) {
        this.userService = userService;
    }
    
    @PostMapping("/login")
    @Operation(summary = "用户登录", description = "使用用户名和密码进行登录")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "登录成功",
            content = @Content(schema = @Schema(implementation = LoginResponse.class))),
        @ApiResponse(responseCode = "400", description = "请求参数错误",
            content = @Content),
        @ApiResponse(responseCode = "500", description = "服务器内部错误",
            content = @Content)
    })
    public ResponseEntity<LoginResponse> login(
            @Parameter(description = "登录请求对象", required = true,
                schema = @Schema(implementation = LoginRequest.class))
            @Valid @RequestBody LoginRequest request) {
        LoginResponse response = userService.login(request);
        return ResponseEntity.ok(response);
    }
}
