package org.example.springjpa.global.code.resultCode;


import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.springjpa.global.code.ErrorCode;
import org.example.springjpa.global.code.ErrorDTO;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorStatus implements ErrorCode {
    // user
    USER_EXIST_GET_ERROR(HttpStatus.OK,"USERID400", "중복된 id입니다."),
    USER_JOIN_ERROR(HttpStatus.OK,"USER401", "회원 가입 실패"),
    USER_LOGIN_ERROR(HttpStatus.OK,"USERID402", "로그인 실패"),


    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "GLOBAL501","서버 오류")
    ;

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    @Override
    public ErrorDTO getReason() {
        return ErrorDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(false)
                .build();
    }

    @Override
    public ErrorDTO getHttpStatusReason() {
        return ErrorDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(false)
                .httpStatus(httpStatus)
                .build();
    }
}
