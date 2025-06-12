package smu.db_project.auth.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import smu.db_project.auth.dto.AuthResponseDto;
import smu.db_project.auth.dto.LoginRequestDto;
import smu.db_project.auth.dto.RegisterRequestDto;
import smu.db_project.auth.service.AuthService;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    // 로그인 엔드포인트
    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody LoginRequestDto request) {
        AuthResponseDto response = authService.login(request);
        return ResponseEntity.ok(response);
    }

    // 회원가입 엔드포인트
    @PostMapping("/register")
    public ResponseEntity<AuthResponseDto> register(@RequestBody RegisterRequestDto request) {

        // 🔍 로그 찍기: 실제 들어오는 값 확인
        System.out.println("회원가입 요청 도착!");
        System.out.println("sNum: " + request.getSNum());
        System.out.println("sId: " + request.getSId());
        System.out.println("name: " + request.getName());
        System.out.println("password: " + request.getPassword());
        AuthResponseDto response = authService.register(request);

        return ResponseEntity.ok(response);
    }
}