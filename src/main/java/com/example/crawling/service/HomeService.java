package com.example.crawling.service;

import com.example.crawling.vo.Book;

import java.util.List;
import java.util.Map;

public interface HomeService {

	List<Map<String, Object>> selectNewsData() throws Exception;

	List<Book> searchMap(Map<String, String> searchKeyword) throws Exception;

	Book findByUrl(String newsUrl) throws Exception;
}
