package com.hongbin.minigame001.controller;

import com.hongbin.minigame001.domain.User;
import com.hongbin.minigame001.mapper.UserMapper;
import jakarta.validation.constraints.NotBlank;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final UserMapper userMapper;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public AuthController(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public record RegisterReq(@NotBlank String username,
                              String email,
                              @NotBlank String password) {}

    @PostMapping("/register")
    public Map<String, Object> register(@RequestBody RegisterReq req) {
        // 简单重复校验（MVP）
        if (userMapper.findByUsername(req.username()) != null) {
            return Map.of("ok", false, "msg", "username exists");
        }
        User u = User.builder()
                .username(req.username())
                .email(req.email())
                .passwordHash(encoder.encode(req.password()))
                .build();
        userMapper.insert(u);
        return Map.of("ok", true, "id", u.getId());
    }
}
