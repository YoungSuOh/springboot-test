package org.example.springjpa.domain.users.controller;


import lombok.RequiredArgsConstructor;
import org.example.springjpa.domain.users.dto.request.UsersRequestDTO;
import org.example.springjpa.domain.users.dto.response.UsersResponseDTO;
import org.example.springjpa.domain.users.service.UsersService;
import org.example.springjpa.global.ApiResponse;
import org.example.springjpa.global.code.resultCode.ErrorStatus;
import org.example.springjpa.global.code.resultCode.SuccessStatus;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UsersController {

    private final UsersService usersService;

    // 회원가입 API
    @PostMapping("/join")
    public ApiResponse<HttpStatus> join(@RequestBody UsersRequestDTO.UsersJoinDTO joinDTO) {
        if(usersService.join(joinDTO)){
            return ApiResponse.onSuccess(HttpStatus.OK, SuccessStatus.USER_JOIN_SUCCESS);
        }else{
            return ApiResponse.onFailure(ErrorStatus.USER_JOIN_ERROR.getCode(),
                    ErrorStatus.USER_JOIN_ERROR.getMessage(), null);
        }
    }

    // 로그인 API
    @PostMapping("/login")
    public ApiResponse<HttpStatus> login(@RequestBody UsersRequestDTO.UsersLoginDTO loginDTO) {
        if (usersService.login(loginDTO)) {
            return ApiResponse.onSuccess(HttpStatus.OK, SuccessStatus.USER_LOGIN_SUCCESS);
        } else {
            return ApiResponse.onFailure(ErrorStatus.USER_LOGIN_ERROR.getCode(),
                    ErrorStatus.USER_LOGIN_ERROR.getMessage(), null);
        }
    }

    // 마이페이지 조회 API
    @GetMapping("/mypage/{userId}")
    public ApiResponse<UsersResponseDTO.UsersMyPageDto> getMyPage(@PathVariable String userId) {
        UsersResponseDTO.UsersMyPageDto myPageDto = usersService.getMyPage(Long.parseLong(userId));
        return ApiResponse.onSuccess(myPageDto, SuccessStatus.USER_MYPAGE_SUCCESS);
    }
}