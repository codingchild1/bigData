package com.example.crawling.crawlingService;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CrawlingRepository extends MongoRepository<CrawlingEntity, String> {

	// media 필드 값을 기반으로 newsNo 필드 값만 가져오는 커스텀 쿼리
	public CrawlingEntity findByMedia(String media);

}
