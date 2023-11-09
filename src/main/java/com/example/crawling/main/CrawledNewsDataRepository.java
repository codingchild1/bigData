package com.example.crawling.main;

import com.example.crawling.vo.NewsVO;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CrawledNewsDataRepository extends MongoRepository<NewsVO, String> {
    public NewsVO findByTitle(String title);
    public List<NewsVO> findByDetail(String detail);
}
