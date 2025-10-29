package com.ssg.todoservice.controller;

import com.ssg.todoservice.dto.TodoDTO;
import com.ssg.todoservice.service.TodoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Log4j2
@Controller
@RequestMapping("/todo")
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    // 리스트 조회
    @GetMapping("/list")
    public void list(Model model) {
        log.info("todo list....");
        model.addAttribute("dtoList", todoService.getAll());
    }

    // todo 등록 + PRG 패턴
    @GetMapping("/register")
    public void registerGET() {
        log.info("GET todo register....");
    }

    @PostMapping("/register")
    public String registerPOST(@Valid TodoDTO todoDTO,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes) {
        log.info("POST todo register");

        //  유효성 검사 오류 발생 시 등록 화면으로 리다이렉트
        //  입력했던 데이터도 todoDTO에 저장하여 같이 전달
        if (bindingResult.hasErrors()) {
            log.error("POST todo register has errors...");
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/todo/register";
        }

        // 등록 성공
        log.info(todoDTO);
        return "redirect:/todo/list";
    }

    @GetMapping("/read")
    public void read(Long tno, Model model) {
        TodoDTO todoDTO = todoService.getOne(tno);
        log.info(todoDTO);
        model.addAttribute("dto", todoDTO);
    }
}
