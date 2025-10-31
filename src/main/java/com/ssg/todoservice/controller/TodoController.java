package com.ssg.todoservice.controller;

import com.ssg.todoservice.dto.PageRequestDTO;
import com.ssg.todoservice.dto.PageResponseDTO;
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
//    @GetMapping("/list")
//    public void list(Model model) {
//        log.info("todo list....");
//        model.addAttribute("dtoList", todoService.getAll());
//    }

    @GetMapping("/list")
    public void list(@Valid PageRequestDTO pageRequestDTO, BindingResult bindingResult, Model model) {
        log.info("PageRequestDTO:" + pageRequestDTO);

        if (bindingResult.hasErrors()) {
            pageRequestDTO = PageRequestDTO.builder().build();
        }

        model.addAttribute("responseDTO", todoService.getList(pageRequestDTO));
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
        todoService.register(todoDTO);
        return "redirect:/todo/list";
    }

    //    조회, 수정 화면은 동일
    @GetMapping({"/read", "/modify"})
    public void read(Long tno, PageRequestDTO pageRequestDTO, Model model) {
        log.info("GET todo detail....");
        TodoDTO todoDTO = todoService.getOne(tno);
        log.info(todoDTO);
        model.addAttribute("dto", todoDTO);
    }

    @PostMapping("/remove")
    public String remove(Long tno, RedirectAttributes redirectAttributes) {
        log.info("POST todo remove....");
        log.info("tno: {}", tno);
        todoService.remove(tno);
        return "redirect:/todo/list";
    }

    @PostMapping("/modify")
    public String modify(@Valid TodoDTO todoDTO,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes) {
        log.info("POST todo modify");

        if (bindingResult.hasErrors()) {
            log.info("POST todo modify has error....");
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            redirectAttributes.addAttribute("tno", todoDTO.getTno());
            return "redirect:/todo/modify";
        }

        log.info(todoDTO);
        todoService.modify(todoDTO);
        return "redirect:/todo/read";
    }
}
