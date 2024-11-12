package org.example.springjpa.global.exception;


import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.springjpa.global.code.ErrorCode;
import org.example.springjpa.global.code.ErrorDTO;

@Getter
@AllArgsConstructor
public class GeneralException extends RuntimeException {
    private ErrorCode code;

    public ErrorDTO getErrorReason(){
        return this.code.getReason();
    }
    public ErrorDTO getErrorReasonHttpStatus(){
        return this.code.getHttpStatusReason();
    }
}
