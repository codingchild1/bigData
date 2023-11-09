package com.example.crawling.main;

import com.example.crawling.vo.Book;
import com.example.crawling.vo.NewsVO;
import kr.co.shineware.nlp.komoran.constant.DEFAULT_MODEL;
import kr.co.shineware.nlp.komoran.core.Komoran;
import kr.co.shineware.nlp.komoran.model.KomoranResult;
import kr.co.shineware.nlp.komoran.model.Token;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.python.util.PythonInterpreter;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootApplication(scanBasePackages = "com.example.crawling.dao")
public class MongoinsertApplication {

    @Resource
    private BookRepository bookRepository;

    @Resource
    private CrawledNewsDataRepository crawledNewsDataRepository;

    private PythonInterpreter interpreter;

    public static void main(String[] args) {
        SpringApplication.run(MongoinsertApplication.class, args);
    }

    @Bean
    public ApplicationRunner applicationRunner() {
        return args -> {

            try {
                bookRepository.deleteAll();   //데이터 삭제
                crawledNewsDataRepository.deleteAll();    //데이터 삭제

                //뉴스번호 7753100번부터 7753110번까지 스크래핑
                for (int newsNo = 7803770; newsNo <= 7803890; newsNo++) {
                    if ((Integer) newsNo == null) break;

                    //크롤링을 하려는 주소
                    final String url = "https://news.kbs.co.kr/news/pc/view/view.do?ncd=" + newsNo;

                    //크롤링
                    Connection conn = Jsoup.connect(url);
                    Document document = conn.get();

                    //뉴스 동영상 URL
//                Element imageUrl = document.getElementsByAttributeValue("alt", "동영상 링크").first();

                    //기사 제목
                    Element title = document.getElementsByClass("headline-title").first();

                    //보도 날짜
                    Element date = document.getElementsByClass("input-date").first();

                    //본문 및 태그 제거 등 기타 내용 필터링
                    Elements detail = document.getElementsByClass("detail-body");
                    String strDetail = detail.html();
                    strDetail = strDetail.replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", " ");
                    strDetail = strDetail.replaceAll("[^ㄱ-ㅎㅏ-ㅣ가-힣a-zA-Z0-9/\\s/g]", "");
                    strDetail = strDetail.replaceAll("서비스 기사   기사본문 필요한 부분을 치환  ", "");
                    strDetail = strDetail.replaceAll("앵커", "");

                    //기자 이름
                    Elements reporter = document.getElementsByClass("reporter-name");
                    String strRepoter = reporter.html();
                    strRepoter = strRepoter.replaceAll("기자", "");

                    //기자 이메일
                    Elements email = document.getElementsByClass("ico-email");

                /* html() : 태그까지 가져오기
                   text() : 내용만 가져오기 */

                    System.out.println("newsNo : " + newsNo);
//                System.out.println("상품 이미지 URL : " + imageUrl.attr("abs:src"));
                    System.out.println("기사 제목 : " + title.text());
                    System.out.println("기사 날짜 : " + date.text());
                    System.out.println("본문 : " + strDetail);
                    System.out.println("기자 : " + strRepoter);
                    System.out.println("이메일 : " + email.text());
//                for (int infoIdx = 0; infoIdx < info.size(); infoIdx++) {
//                    System.out.println(productInfo[infoIdx] + info.get(infoIdx).text());
//                }
                    System.out.println("---------------------------");

                    //DB에 collection 에 접근 하기 위한 VO 객체
                    Book book = new Book();
                    book.setNewsNo(newsNo);
                    book.setTitle(title.text());
                    book.setDate(date.text());
                    book.setDetail(strDetail);
                    book.setReporter(strRepoter);
                    book.setEmail(email.text());

                    //DB에 연결하기 위한 Repository
                    bookRepository.insert(book);


                }

                //DB 조회
                List<Map<String, Object>> list = new ArrayList<>();
                for (Book books : bookRepository.findAll()) {
                    Map<String, Object> map = new HashMap<>();
//                System.out.println(book.getTitle().toString());
                    map.put("newsNo" ,books.getNewsNo());
                    map.put("title" ,books.getTitle().toString());
                    map.put("date" ,books.getDate().toString());
                    map.put("detail" ,books.getDetail().toString());
                    map.put("reporter" ,books.getReporter().toString());
                    map.put("email" ,books.getEmail().toString());
                    list.add(map);
                }

                //형태소 분석을 위한 Komoran
                Komoran komoran = new Komoran(DEFAULT_MODEL.FULL);
                for (int i = 0; i < list.size(); i++) {
                    String strToAnalyzeTitle = list.get(i).get("title").toString();
                    String strToAnalyzeDetail = list.get(i).get("detail").toString();

                    //형태소 분석
                    //문자열을 parameter 로 사용
                    KomoranResult analyzeTitleResultList = komoran.analyze(strToAnalyzeTitle);
                    KomoranResult analyzeDetailResultList = komoran.analyze(strToAnalyzeDetail);

//                    System.out.println(analyzeTitleResultList.getPlainText());
//                    System.out.println(analyzeDetailResultList.getPlainText());

                    //토큰화 작업
                    List<Token> tokenListTitle = analyzeTitleResultList.getTokenList();
                    List<Token> tokenListDetail = analyzeDetailResultList.getTokenList();

                    //품사 태깅을 위한 작업
                    //NNG: 일반명사, NNP: 고유명사
                    String tokenTitleStr = "";
                    String tokenDetailStr = "";
                    for (int j = 0; j < tokenListTitle.size(); j++) {
                        if (tokenListTitle.get(j).getPos().equals("NNP") || tokenListTitle.get(j).getPos().equals("NNG")) {
                            tokenTitleStr += tokenListTitle.get(j).getMorph() + ", ";
                        }
                    }
                    for (int j = 0; j < tokenListDetail.size(); j++) {
                        if (tokenListDetail.get(j).getPos().equals("NNP") || tokenListDetail.get(j).getPos().equals("NNG")) {
//                        System.out.format("(%2d, %2d) %s/%s\n", token.getBeginIndex(), token.getEndIndex(), token.getMorph(), token.getPos());
                            tokenDetailStr += tokenListDetail.get(j).getMorph() + ", ";
                        }
                    }

                    //형태소 분석한 데이터를 넣기 위한 Collection 생성 및 접근 VO 객체
                    NewsVO newsVO = new NewsVO();
                    newsVO.setNewsNo(Integer.parseInt(list.get(i).get("newsNo").toString()));
                    newsVO.setTitle(tokenTitleStr);
                    newsVO.setDate(list.get(i).get("date").toString());
                    newsVO.setDetail(tokenDetailStr);
                    newsVO.setReporter(list.get(i).get("reporter").toString());
                    newsVO.setEmail(list.get(i).get("email").toString());

                    crawledNewsDataRepository.insert(newsVO);
                }
                System.out.println("형태소 분석 완료");
            } catch (Exception e) {
                e.printStackTrace();
            }


        };
    }
}
