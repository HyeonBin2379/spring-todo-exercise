package com.ssg.todoservice.mappers;

import com.ssg.todoservice.domain.TodoVO;
import com.ssg.todoservice.mapper.TodoMapper;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.List;

@Log4j2
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class TodoMapperTests {

    // Mapper 인터페이스이므로 생성자 불필요 -> required=false
    @Autowired(required = false)
    private TodoMapper todoMapper;

    @Test
    public void testGetTime() {
        log.info(todoMapper.getTime());
    }

    @Test
    public void testInsert() {
        TodoVO todoVO = TodoVO.builder()
                .title("게시글 입력 테스트")
                .dueDate(LocalDate.parse("2025-12-31"))
                .writer("tester")
                .build();
        todoMapper.insert(todoVO);
    }

    @Test
    public void testGetAll() {
        List<TodoVO> todoList = todoMapper.selectAll();
        todoList.forEach(log::info);
    }

    @Test
    public void testGetOne() {
        Long tno = 1L;
        TodoVO todoVO = todoMapper.selectOne(1L);
        log.info(todoVO);
        Assertions.assertNotNull(todoVO);
    }
}
