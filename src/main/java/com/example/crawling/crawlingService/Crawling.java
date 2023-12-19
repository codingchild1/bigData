package com.example.crawling.crawlingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class Crawling {

//	@Autowired
//	CrawlingLogicServiceGwnews crawlingLogicServiceGwnews;

	@Autowired
	CrawlingLogicServiceETNEWS crawlingLogicServiceETNEWS;

//	@Autowired
//	CrawlingLogicServiceBOANNEWS crawlingLogicServiceBOANNEWS;

//	@Autowired
//	CrawlingLogicServiceBLOTER crawlingLogicServiceBLOTER;

	@PostConstruct
	public void scheduleCrawlTask() throws Exception{
		/*강원신문*/
//		crawlingLogicServiceGwnews.crawlWebsite();
		
		/*전자신문*/
		crawlingLogicServiceETNEWS.resetNewsNum();
		crawlingLogicServiceETNEWS.crawlWebsite();

		/*보안신문*/
//		crawlingLogicServiceBOANNEWS.crawlWebsite();

		/*블로터*/
//		crawlingLogicServiceBLOTER.crawlWebsite();

	}
}
