package com.example.crawling.controller;

import com.example.crawling.dao.BoardRepository;
import com.example.crawling.dto.BoardSaveDto;
import com.example.crawling.service.HomeService;
import com.example.crawling.util.SangwonUtil;
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
    public String test(Model model) throws Exception {

	    SangwonUtil util = new SangwonUtil();

		try {
			List<Map<String, Object>> list = homeService.selectNewsData();
			model.addAttribute("newsDataList", list);
		} catch (Exception e) {
			e.printStackTrace();
		}

        return "index";
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

	@GetMapping("/search")
	public String getSearch(Model model, @RequestParam String keyword, @RequestParam(name = "page", required = false, defaultValue = "0") Integer page) throws Exception{
		Map<String, String> map = new HashMap<>();

		try {
			map.put("keyword", keyword);
			map.put("searchType", "content");
			map.put("page", page.toString());
			List<Book> searchMap = homeService.searchMap(map);   // 검색용

			model.addAttribute("searchResult", searchMap);
			model.addAttribute("keyword", keyword);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "search";
	}

	@PostMapping("/search")
	public ResponseEntity<List<Book>> postSearch(@RequestParam Map<String, String> param, @RequestParam String keyword, @RequestParam(name = "page", required = false, defaultValue = "0") Integer page) throws Exception{
		Map<String, String> map = new HashMap<>();

			map.put("keyword", keyword);
			map.put("searchType", "content");
			map.put("page", page.toString());
			List<Book> searchMap = homeService.searchMap(map);   // 검색용

//			mv.addObject("searchResult", searchMap);
//			mv.addObject("keyword", keyword);

		return new ResponseEntity<List<Book>>(searchMap, HttpStatus.OK);
	}

	@PutMapping("test/{id}")
	public void update(@RequestBody BoardSaveDto dto, @PathVariable String id) {
		Board board = new Board();
		board.set_id(id);   // save 함수는 같은 아이디 일 경우 수정한다.

		boardRepository.save(board);
	}
}

