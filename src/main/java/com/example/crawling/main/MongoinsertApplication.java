package com.example.crawling.main;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.MongoRepository;

@SpringBootApplication
public class MongoinsertApplication {

    @Autowired
    private MongoRepository mongoRepository;

    public static void main(String[] args) {
        SpringApplication.run(MongoinsertApplication.class, args);
    }

    @Bean
    public ApplicationRunner applicationRunner() {
        return args -> {

            //뉴스번호 7753100번부터 7753110번까지 스크래핑
            for (int newsNo = 7803750; newsNo <= 7803770; newsNo++) {

                final String url = "https://news.kbs.co.kr/news/pc/view/view.do?ncd="+newsNo;

                try {
                    Connection conn = Jsoup.connect(url);
                    Document document = conn.get();

                    //뉴스 동영상 URL
//                Element imageUrl = document.getElementsByAttributeValue("alt", "동영상 링크").first();

                    //기사 제목
                    Element title = document.getElementsByClass("headline-title").first();

                    //보도 날짜
                    Element date = document.getElementsByClass("input-date").first();

                    //본문
                    Elements detail = document.getElementsByClass("detail-body");

                    //기자 이름
                    Elements reporter = document.getElementsByClass("reporter-name");

                    //기자 이메일
                    Elements email = document.getElementsByClass("ico-email");

                /* html() : 태그까지 가져오기
                   text() : 내용만 가져오기 */

                    System.out.println("newsNo : " + newsNo);
//                System.out.println("상품 이미지 URL : " + imageUrl.attr("abs:src"));
                    System.out.println("기사 제목 : " + title.text());
                    System.out.println("기사 날짜 : " + date.text());
                    System.out.println("본문 : " + detail.html());
                    System.out.println("기자 : " + reporter.text());
                    System.out.println("이메일 : " + email.text());
//                for (int infoIdx = 0; infoIdx < info.size(); infoIdx++) {
//                    System.out.println(productInfo[infoIdx] + info.get(infoIdx).text());
//                }
                    System.out.println("---------------------------");

                    Book book = new Book();
                    book.setNewsNo(newsNo);
                    book.setTitle(title.text());
                    book.setDate(date.text());
                    book.setDetail(detail.html());
                    book.setReporter(reporter.text());
                    book.setEmail(email.text());

                    mongoRepository.insert(book);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        };
    }
}
