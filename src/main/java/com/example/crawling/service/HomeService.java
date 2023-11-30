package com.example.crawling.service;

import java.util.List;
import java.util.Map;

public interface HomeService {

	List<Map<String, Object>> selectNewsData() throws Exception;
}
