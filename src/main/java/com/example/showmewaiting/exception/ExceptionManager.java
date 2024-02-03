package com.example.showmewaiting.exception;


import com.example.showmewaiting.domain.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionManager {
    // (1) 모든 RuntimeException 에러가 발생시 동작
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> runtimeExceptionHandler(RuntimeException e){   // ? 는 모든 값이 올 수 있다는 것
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)      // 서버 에러 상태 메시지와 body에 에러상태 메시지(문자열)을 넣어 반환해줌
                .body(Response.error(e.getMessage()));
    }

    // (2) 기존에 만들어둔 에러(HospitalReviewAppException)가 발생시 동작
    @ExceptionHandler(UserException.class)
    public ResponseEntity<?> hospitalReviewAppExceptionHandler(UserException e){
        return ResponseEntity.status(e.getErrorCode().getStatus())
                .body(Response.error(e.getErrorCode().name()));
    }
}
