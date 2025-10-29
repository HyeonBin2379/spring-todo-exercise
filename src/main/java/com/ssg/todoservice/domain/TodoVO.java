package com.ssg.todoservice.domain;

import lombok.*;

import java.time.LocalDate;

// 도메인 계층: DB의 원본 데이터를 저장
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TodoVO {

    private Long tno;
    private String title;
    private LocalDate dueDate;
    private String writer;
    private boolean finished;
}
