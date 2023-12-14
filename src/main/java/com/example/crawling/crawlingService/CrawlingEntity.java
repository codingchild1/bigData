package com.example.crawling.crawlingService;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data   /*getter setter toString*/
@NoArgsConstructor  /*기본 생성자를 유지하고 인자를 받는 생성자*/
@AllArgsConstructor /*모든 필드를 가지고 있는 생성자를 자동으로 생성*/
@Document("news_no_db")
public class CrawlingEntity {

	@Id
	/*강원신문*/
	private String media;

	/*서울신문*/
	private long newsNo;

}
