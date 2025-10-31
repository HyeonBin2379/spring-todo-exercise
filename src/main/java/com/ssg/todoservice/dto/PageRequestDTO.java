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
    @Min(value = 1)
    @Max(value = 100)
    @Positive
    private int size = 10;
    
    private String link;
    private String[] types;
    private String keyword;
    private boolean finished;
    
    private LocalDate from;
    private LocalDate to;
    
    public int getSkip() {
        return (page-1)*10;
    }

    public String getLink() {
        return this.link = (this.link == null)
                ? String.format("page=%d&size=%d", page, size)
                : this.link;
    }
}
