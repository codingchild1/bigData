package com.example.crawling.controller;

import com.example.crawling.dao.BoardRepository;
import com.example.crawling.dto.BoardSaveDto;
import com.example.crawling.service.HomeService;
import com.example.crawling.vo.Board;
import com.example.crawling.vo.Book;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Controller
public class HomeController {

	@Autowired
	HomeService homeService;

    private final BoardRepository boardRepository;

    @RequestMapping("/index")
    public String test(Model model) throws Exception {

		try {
			List<Map<String, Object>> list = homeService.selectNewsData();

			Map<String, String> map = new HashMap<>();
			map.put("keyword", "중국");
			map.put("searchType", "content");
			List<Book> searchMap = homeService.searchMap(map);   // 검색용

			model.addAttribute("newsDataList", list);
			System.out.println(searchMap);
			System.out.println("aaa");
		} catch (Exception e) {
			e.printStackTrace();
		}

        return "/index";
    }

    @RequestMapping("/search")
    public String test2(Model model) {

        String name = "name";

        model.addAttribute("name", name);
        return "/search";
    }

    @PutMapping("test/{id}")
    public void update(@RequestBody BoardSaveDto dto, @PathVariable String id) {
        Board board = new Board();
        board.set_id(id);   // save 함수는 같은 아이디 일 경우 수정한다.

        boardRepository.save(board);
    }
}


