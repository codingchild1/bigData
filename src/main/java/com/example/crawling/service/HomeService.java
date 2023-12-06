package com.example.crawling.service;

import com.example.crawling.vo.Book;

import java.util.List;
import java.util.Map;

public interface HomeService {

	long countByTitleRegex(String title) throws Exception;

	long countByDetailRegex(String detail) throws Exception;

	long countByReporterRegex(String reporter) throws Exception;

	long getTotalRowCount() throws Exception;

	List<Book> getLatestNews() throws Exception;

	List<Map<String, Object>> selectNewsData() throws Exception;

	Map<String, Object> searchMap(Map<String, String> searchKeyword) throws Exception;

	Book findByUrl(String newsUrl) throws Exception;
}
