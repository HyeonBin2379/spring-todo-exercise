package com.ssg.todoservice.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.Min;
import java.util.List;

// 제네릭 타입 사용: 확장성을 높이기 위해 사용 - 회원, 입고, 출고 게시판 등에서 모두 페이지네이션 적용 가능
// 컨트롤러를 통해 이 DTO를 jsp 뷰로 전달하여
@Getter
@ToString
public class PageResponseDTO<E> {

    private int page;
    private int size;
    private int total;

    //시작 페이지 번호
    @Min(value = 1)
    private int start;
    //끝 페이지 번호
    private int end;

    //이전 페이지의 존재 여부
    private boolean prev;
    //다음 페이지의 존재 여부
    private boolean next;

    private List<E> dtoList;

    //  withAll이란 메서드 내부에서 빌더 패턴대로 PageResponseDTO 생성
    @Builder(builderMethodName = "withAll")
    public PageResponseDTO(PageRequestDTO pageRequestDTO, List<E> dtoList, int total){

        this.page = pageRequestDTO.getPage();
        this.size = pageRequestDTO.getSize();

        this.total = total;
        this.dtoList = dtoList;

        // 화면에 출력할 마지막 페이지 번호부터 계산
        // 현재 페이지/10.0의 값을 올림처리한 후 10을 곱함
        // 올림 적용 이유: 10개로 나누어떨어지지 않고 남은 나머지 행에 대해서도 페이지를 생성해야 하기 때문
        this.end =   (int)(Math.ceil(this.page / 10.0 )) *  10;

        // 화면에 출력할 페이지 번호의 시작값: 앞서 구한 마지막 페이지 번호를 활용하여 계산
        this.start = this.end - 9;

        // 페이지 번호 리스트 출력 시, 마지막 페이지 번호값 계산
        int last = (int)(Math.ceil((total/(double)size)));
        // todo가 삭제되면서 마지막 페이지 번호에 변경이 발생했을 때, 마지막 페이지 번호를 갱신
        this.end = Math.min(last, this.end);

        // 이전/이후 버튼 클릭 시 페이지 번호 리스트 변경 조건 지정
        this.prev = this.start > 1;
        this.next =  total > Math.min(this.end, last) * this.size;
    }
}
