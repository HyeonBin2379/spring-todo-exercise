package com.ssg.todoservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PageRequestDTO {

    @Builder.Default
    @Min(value = 1)
    @Positive
    private int page = 1;   // 한 페이지의 시작번호는 1페이지부터

    // 한 페이지에 담길 글의 개수   
    @Builder.Default
    @Min(value = 10)
    @Max(value = 100)
    @Positive
    private int size = 10;

    // 리스트 페이지 유지용 링크 쿼리
    private String link;

    // 검색 타입들: "t"(title), "w"(writer)
    private String[] types;

    // 검색어
    private String keyword;

    // 완료 여부 필터
    private boolean finished;

    // 기간 검색
    private LocalDate from;
    private LocalDate to;

    // limit offset
    public int getSkip() {
        return (page-1)*10;
    }

    public String getLink() {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("page=%d&size=%d", page, size));

        return builder.toString();
    }
}
