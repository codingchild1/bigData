package com.example.crawling.main;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.MongoRepository;

import javax.annotation.Resource;

// @SpringBootApplication
public class MongoinsertApplication {

    @Resource
    private MongoRepository mongoRepository;

    public static void main(String[] args) {
        SpringApplication.run(MongoinsertApplication.class, args);
    }

    @Bean
    public ApplicationRunner applicationRunner() {
        return args -> {

            //뉴스번호 7753100번부터 7753110번까지 스크래핑
            for (int newsNo = 7803750; newsNo <= 7803760; newsNo++) {

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
                    String strDetail = detail.html();
                    strDetail = strDetail.replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", " ");
                    strDetail = strDetail.replaceAll("[^ㄱ-ㅎㅏ-ㅣ가-힣a-zA-Z0-9/\\s/g]", "");
                    strDetail = strDetail.replaceAll("서비스 기사   기사본문 필요한 부분을 치환  ", "");

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
                    System.out.println("본문 : " + strDetail);
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
                    book.setDetail(strDetail);
                    book.setReporter(reporter.text());
                    book.setEmail(email.text());
                    book.setTest("테스트");

                    mongoRepository.insert(book);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        };
    }
}
