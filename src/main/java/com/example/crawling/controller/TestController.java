package com.example.crawling.controller;

import com.example.crawling.dao.BoardRepository;
import com.example.crawling.dto.BoardSaveDto;
import com.example.crawling.vo.Board;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@RequiredArgsConstructor
@Controller
public class TestController {

    private final BoardRepository boardRepository;

    @RequestMapping("/index")
    public String test(Model model) {

        String name = "name";

        model.addAttribute("name", name);
        return "/index";
    }

    @PutMapping("test/{id}")
    public void update(@RequestBody BoardSaveDto dto, @PathVariable String id) {
        Board board = new Board();
        board.set_id(id);   // save 함수는 같은 아이디 일 경우 수정한다.

        boardRepository.save(board);
    }
}


