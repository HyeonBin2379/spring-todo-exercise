package com.ssg.todoservice.mapper;

import com.ssg.todoservice.domain.TodoVO;

public interface TodoMapper {

    String getTime();
    void insert(TodoVO todoVO);

}
