package com.example.crawling.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data   /*getter setter toString lombok*/
@NoArgsConstructor  /*기본 생성자를 유지하고 인자를 받는 생성자*/
@AllArgsConstructor /*모든 필드를 가지고 있는 생성자를 자동으로 생성*/
@Document(collection = "crawling")  /*MongoDB의 test 컬렉션과 연결하도록 설정*/
public class Book {
    @Id
    private int newsNo;
    private String url;    // URL 타입?
    private String title;
    private String date;
    private String detail;
    private String reporter;
    private String email;
    private String nontagDetail;
    private String nontagTitle;
    private String analyzeDetail;
    private String analyzeTitle;
    private List<?> nounDetail;
    private List<?> nounTitle;
    private String crawlDate;
	private String media;
	private String img;

//    public Book() {}

//    public Book( String title, String date, String detail, String reporter, String email) {
//        this.title = title;
//        this.date = date;
//        this.detail = detail;
//        this.reporter = reporter;
//        this.email = email;
//    }

//    @Override
//    public String toString() {
//        return String.format(
//                "Book[newsNo=%s, title='%s', date='%s', detail='%s', reporter='%s', email='%s']",
//                newsNo, title, date, detail, reporter, email);
//
//    }
}
