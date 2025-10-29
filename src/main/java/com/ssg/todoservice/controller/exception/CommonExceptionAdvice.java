package com.ssg.todoservice.controller.exception;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

// 컨트롤러에 대해 공통으로 처리할 작업을 Advice로 지정
// 스프링 컨트롤러에서 발생하는 예외를 처리하는 일반적인 방식
// @ControllerAdvice: 스프링 컨트롤러에서 사용할 어드바이스임을 명시 + 스프링 빈으로 등록
@ControllerAdvice
@Log4j2
public class CommonExceptionAdvice {

    // 작성한 예외 메시지를 그대로 브라우저에게 전송
    @ResponseBody
    @ExceptionHandler(value = NumberFormatException.class)
    public String exceptNumber(NumberFormatException numberFormatException) {
        log.error("numberFormatException: " + numberFormatException.getMessage());
        return "Number Format Exception!";
    }

    @ExceptionHandler(value = NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)     // 예외 발생 시 전달할 상태코드를 지정
    public String notFound() {
        return "custom404";
    }
}
