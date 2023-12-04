package com.example.crawling.controller;

import com.example.crawling.dao.BoardRepository;
import com.example.crawling.dto.BoardSaveDto;
import com.example.crawling.service.HomeService;
import com.example.crawling.vo.Board;
import com.example.crawling.vo.Book;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public String test(Model model, String newsUrl) throws Exception {

		try {
			List<Map<String, Object>> list = homeService.selectNewsData();

			Map<String, String> map = new HashMap<>();
			map.put("keyword", "중국");
			map.put("searchType", "title");
			List<Book> searchMap = homeService.searchMap(map);   // 검색용
			model.addAttribute("newsDataList", list);
			System.out.println(searchMap);
			System.out.println("aaa");
		} catch (Exception e) {
			e.printStackTrace();
		}

        return "/index";
    }

	@RequestMapping("/detail")
	@ResponseBody
	public ResponseEntity<Book> newsUrl(@RequestParam("newsUrl") String newsUrl) throws Exception{

		Book detail = new Book();

		try {
			detail = homeService.findByUrl(newsUrl);
			// mv.addObject("title", detail);
			// System.out.println("");
		}catch (Exception e) {
			e.printStackTrace();
		}

		return new ResponseEntity<Book>(detail, HttpStatus.OK);
	}

    @PutMapping("test/{id}")
    public void update(@RequestBody BoardSaveDto dto, @PathVariable String id) {
        Board board = new Board();
        board.set_id(id);   // save 함수는 같은 아이디 일 경우 수정한다.

        boardRepository.save(board);
    }

	@RequestMapping("/search")
	public String test2(Model model) {

		String name = "name";

		model.addAttribute("name", name);
		return "/search";
	}
}


