package com.ssg.todoservice.controller.formatter;

import org.springframework.format.Formatter;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

// 날짜/형변환에 관한 커스터마이징을 수행할 때 주로 사용
// servlet-context.xml에서 빈으로 등록
public class LocalDateFormatter implements Formatter<LocalDate> {

    // 타입 변환
    @Override
    public LocalDate parse(String text, Locale locale) throws ParseException {
        return LocalDate.parse(text, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    @Override
    public String print(LocalDate object, Locale locale) {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd").format(object);
    }
}
