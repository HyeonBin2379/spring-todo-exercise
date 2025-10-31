package com.ssg.todoservice.mapper;

import com.ssg.todoservice.domain.TodoVO;
import com.ssg.todoservice.dto.PageRequestDTO;

import java.util.List;

public interface TodoMapper {

    String getTime();
    void insert(TodoVO todoVO);
    List<TodoVO> selectAll();
    List<TodoVO> selectList(PageRequestDTO pageRequestDTO);
    TodoVO selectOne(Long tno);
    void delete(Long tno);
    void update(TodoVO todoVO);
    int getCount();
}
