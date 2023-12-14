package com.example.crawling.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data   /*getter setter toString*/
@NoArgsConstructor  /*기본 생성자를 유지하고 인자를 받는 생성자*/
@AllArgsConstructor /*모든 필드를 가지고 있는 생성자를 자동으로 생성*/
@Document(collection = "news_analyze_data")  /*MongoDB의 test 컬렉션과 연결하도록 설정*/
public class NewsVO {

    @Id
    private long newsNo;
    private String title;
    private String date;
    private String detail;
    private String reporter;
    private String email;

}
