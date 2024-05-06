package com.example.controller.auth;

import com.example.api.ApiResult;
import com.example.dto.request.LoginRequest;
import com.example.dto.request.PointModifyRequest;
import com.example.dto.request.SignUpRequest;
import com.example.exception.CustomException;
import com.example.exception.ErrorCode;
import com.example.service.auth.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@Tag(name = "인증 API")
public class AuthController {

    private final AuthService authService;

    /*
    회원가입 post 요청
     */
    @PostMapping("/auth/signup")
    @Operation(summary = "회원가입", description = "아이디 중복 요청의 경우 409 에러를 반환한다.")
    public ApiResult<?> signUp(@Valid @RequestBody SignUpRequest signUpRequest){

        return authService.signUp(signUpRequest);
    }

    /*
    로그인 post 요청
     */
    @PostMapping("/auth/login")
    @Operation(summary = "로그인", description = "Http 응답 헤더에 " +
            "(Authorization : 엑세스 토큰 / refresh-token : 리프레시 토큰 / refresh-token-exp-time : 리프레시 토큰 만료시간) 삽입")
    public ApiResult<?> login(@Valid @RequestBody LoginRequest loginRequest, HttpServletResponse response){

        return authService.login(loginRequest, response);
    }

    /*
    로그아웃
     */
    @GetMapping("/auth/logout")
    @Operation(summary = "로그아웃", description = "로그아웃이 정상적으로 작동하지 않은 경우 500 에러를 반환한다.")
    public ApiResult<?> logout(HttpServletRequest request, HttpServletResponse response){

        return authService.logout(request, response);
    }

    /*
    포인트수정
     */
    @PostMapping("/auth/point")
    @Operation(summary = "포인트 수정", description = "")
    public ApiResult<?> modifyPoint(@RequestBody PointModifyRequest pointModifyRequest){

        return authService.modifyPoint(pointModifyRequest);
    }

    /*
    사용자 정보 조희
     */


    @GetMapping("/test")
    public ApiResult<?> test(){
        return ApiResult.success("성공");
    }

    @RequestMapping("/forbidden")
    public String forbidden(){
        throw new CustomException(ErrorCode.MEMBER_NO_PERMISSION);
    }

    @RequestMapping("/unauthorized")
    public String unauthorized(){
        throw new CustomException(ErrorCode.UNAUTHORIZED);
    }

}
