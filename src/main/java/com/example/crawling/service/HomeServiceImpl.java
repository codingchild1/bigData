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

	public long countByTitleRegex(String title) throws Exception {
		return (long) bookRepository.countByTitleRegexOrderByDateDesc(title);
	}

	public long countByDetailRegex(String detail) throws Exception {
		return (long) bookRepository.countByDetailRegexOrderByDateDesc(detail);
	}

	public long countByReporterRegex(String reporter) throws Exception {
		return (long) bookRepository.countByReporterRegexOrderByDateDesc(reporter);
	}

	@Override
	public long getTotalRowCount() throws Exception {
		return (long) bookRepository.count();
	}

	@Override
	public List<Book> getLatestNews() {
		List<Book> latestNews = new ArrayList<Book>();
		try {
			latestNews = (List<Book>) bookRepository.findFirstByOrderByDateDesc();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return latestNews;
	}

	@Override
	public List<Map<String, Object>> selectNewsData() throws Exception {

		List<Map<String, Object>> list = new ArrayList<>();
		for (Book books : bookRepository.findAllByOrderByDateDesc()) {
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
	public Map<String, Object> searchMap(Map<String, String> searchKeyword) throws Exception {

		Map<String, Object> m = new HashMap<String, Object>();

		List<Book> result = new ArrayList<Book>();
		long rowcount = 0;

		int PageSize = 5;
		int nowPage = Integer.parseInt(searchKeyword.get("page"));

		String keyword = (String) searchKeyword.get("keyword");
		String searchType = (String) searchKeyword.get("searchType");

		PageRequest pageable = PageRequest.of(nowPage, PageSize);

		switch (searchType) {
			case "title":
				rowcount = (long) bookRepository.countByTitleRegexOrderByDateDesc(keyword);
				Page<Book> title = bookRepository.findByTitleRegex(keyword, pageable);
				result = title.getContent();
				break;
			case "content":
				rowcount = (long) bookRepository.countByDetailRegexOrderByDateDesc(keyword);
				Page<Book> content = bookRepository.findByDetailRegex(keyword, pageable);
				result = content.getContent();
				break;
			case "reporter":
				rowcount = (long) bookRepository.countByReporterRegexOrderByDateDesc(keyword);
				Page<Book> reporter = bookRepository.findByReporterRegex(keyword, pageable);
				result = reporter.getContent();
				break;
		}

		int resultSize = result.size();
		for (int searchSize = 0; searchSize < resultSize; searchSize++) {
			String[] emailSplit = result.get(searchSize).getEmail().split("\\s+");
			result.get(searchSize).setEmail(emailSplit[0]);
		}

		m.put("rowcount", rowcount);
		m.put("result", result);

		return m;
	}

	@Override
	public Book findByUrl(String newsUrl) throws Exception {
		Book list = bookRepository.findByUrl(newsUrl);
		return list;
	}
}
