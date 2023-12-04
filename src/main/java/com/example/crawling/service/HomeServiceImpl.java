package com.example.crawling.service;

import com.example.crawling.dao.BookRepository;
import com.example.crawling.vo.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class HomeServiceImpl implements HomeService {

	@Autowired
	private BookRepository bookRepository;

	@Override
	public List<Map<String, Object>> selectNewsData() throws Exception {

		List<Map<String, Object>> list = new ArrayList<>();
		for (Book books : bookRepository.findAll()) {
			Map<String, Object> map = new HashMap<>();
			map.put("url", books.getUrl());
			map.put("newsNo", books.getNewsNo());
			map.put("title", books.getTitle());
			map.put("date", books.getDate());
			map.put("detail", books.getDetail());
			map.put("reporter", books.getReporter());
			map.put("email", books.getEmail());
			map.put("nontagDetail", books.getNontagDetail());
			map.put("nontagTitle", books.getNontagTitle());
			map.put("analyzeDetail", books.getAnalyzeDetail());
			map.put("analyzeTitle", books.getAnalyzeTitle());
			map.put("nounDetail", books.getNounDetail());
			map.put("nounTitle", books.getNounTitle());
			map.put("crawlDate", books.getCrawlDate());
			map.put("media", books.getMedia());
			list.add(map);
		}

		return list;
	}

	@Override
	public List<Book> searchMap(Map<String, String> searchKeyword) throws Exception {

		List<Book> result = new ArrayList<>();

		PageRequest pageable = PageRequest.of(0, 5);

				switch (searchKeyword.get("searchType")) {
			case "title":
				result = bookRepository.findByTitleRegex(searchKeyword.get("keyword"));
				break;
			case "content":
				Page<Book> test = bookRepository.findByDetailRegex(searchKeyword.get("keyword"), pageable);
				result = test.getContent();
//				result = bookRepository.findByDetailRegex(searchKeyword.get("keyword"));
				break;
			case "reporter":
				result = bookRepository.findByReporterRegex(searchKeyword.get("keyword"));
				break;
		}

		int resultSize = result.size();
		for (int searchSize = 0; searchSize < resultSize; searchSize++) {
			String[] emailSplit = result.get(searchSize).getEmail().split("\\s+");
			result.get(searchSize).setEmail(emailSplit[0]);
		}
		return result;
	}

	@Override
	public Book findByUrl(String newsUrl) throws Exception {
		Book list = bookRepository.findByUrl(newsUrl);
		return list;
	}
}
