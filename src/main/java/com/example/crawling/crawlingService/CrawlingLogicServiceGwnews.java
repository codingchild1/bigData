package com.example.crawling.crawlingService;

import com.example.crawling.dao.BookRepository;
import com.example.crawling.dao.CrawledNewsDataRepository;
import com.example.crawling.vo.Book;
import kr.co.shineware.nlp.komoran.constant.DEFAULT_MODEL;
import kr.co.shineware.nlp.komoran.core.Komoran;
import kr.co.shineware.nlp.komoran.model.KomoranResult;
import kr.co.shineware.nlp.komoran.model.Token;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CrawlingLogicServiceGwnews {
	private static final Logger logger = LoggerFactory.getLogger(CrawlingLogicServiceGwnews.class);

	@Resource
	private BookRepository bookRepository;

	@Resource
	private CrawlingRepository crawlingRepository;

	@Resource
	private CrawledNewsDataRepository crawledNewsDataRepository;

	@Scheduled(cron = "0 */30 5/6 * * MON-FRI") // 매시 30분마다 6시간 텀으로 실행 (5시30분 기준) 5:30... 11:30...

	public void crawlWebsite() throws NullPointerException {

		// 서울 현재 날짜 (년월일) Time 라이브러리
		LocalDate localDate = LocalDate.now(ZoneId.of("Asia/Seoul"));
		String koreaDate = localDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

		//크롤링 한 시간 log message 출력
		logger.info("fixedRate: 크롤링 시간 - {}", koreaDate);

		/*크롤링 변수 - DB에 저장 및 불러오기 구현 start (마지막에 크롤링한 번호 남기기 위함)*/
		/*강원신문은 40개 정도*/
		/*https://www.gwnews.org/news/articleView.html?idxno=*/
		String gangwonNews = "newsByGangwonNewspaper";
		CrawlingEntity newsNoByGangwonNewspaperList = crawlingRepository.findByMedia(gangwonNews);
		long firstNewsNoByGangwonNewspaper = newsNoByGangwonNewspaperList.getNewsNo();  //크롤링 시작 번호
		long newsNoByGangwonNewspaper = newsNoByGangwonNewspaperList.getNewsNo();       //크롤링 데이터 카운트
		long lastNewsNoByGangwonNewspaper = newsNoByGangwonNewspaper + 50;              //크롤링 끝 번호

		/*서울신문*/
		// 패턴 : 날짜 + 500 + 001 ~ 999
		/*https://www.seoul.co.kr/news/newsView.php?id=20231209500040*/
		long newsNoBySeoulNewspaper = 20231209500040L; /* java 에서 long 자료형은 숫자 뒤에 L 필수 */
		/*크롤링 변수 - DB에 저장 및 불러오기 구현 end (마지막에 크롤링한 번호 남기기 위함)*/

		CrawlingEntity crawlingEntity = new CrawlingEntity();

//		bookRepository.deleteAll();   //데이터 삭제
//		crawledNewsDataRepository.deleteAll();    //데이터 삭제

		List<Map<String, Object>> originData = new ArrayList<>();

		/*뉴스번호 240500 ~ 240900 스크래핑*/
		for (long newsNo = (int) newsNoByGangwonNewspaper; newsNo <= lastNewsNoByGangwonNewspaper; newsNo++) {
			try {
				// 현재 날짜/시간
				LocalDateTime now = LocalDateTime.now();
				// 포맷팅
				String formatedNow = now.format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH시 mm분 ss초"));

				//크롤링을 하려는 주소
				final String url = "https://www.gwnews.org/news/articleView.html?idxno=" + newsNo;

				//크롤링
				Connection conn = Jsoup.connect(url);
				Document document = conn.get();

				//뉴스 동영상 URL
//                Element imageUrl = document.getElementsByAttributeValue("alt", "동영상 링크").first();

				//기사 제목
				Element title = document.getElementsByClass("heading").first();
				String strTitle = title.toString();
				Document nTT = Jsoup.parse(strTitle);
				String strNTT = nTT.text();             //크롤링 할 때 태그 삭제해서 가져오기
//				strTitle = strTitle.replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", "");
				strTitle = strTitle.replaceAll("[^ㄱ-ㅎㅏ-ㅣ가-힣a-zA-Z0-9/\\s/g]", "");

				//보도 날짜
				String date = document.getElementsByClass("infomation").text().split(" ")[4];

				//본문 및 태그 제거 등 기타 내용 필터링
				String detail = document.getElementById("article-view-content-div").text();
				String strDetail = detail;

				Map<String, Object> map = new HashMap<>();
				map.put("originTitle", title);
				map.put("originDetail", detail);
				map.put("url", url);
				map.put("regDate", formatedNow);
				originData.add(map);

				strDetail = strDetail.replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", "");
				strDetail = strDetail.replaceAll("[^ㄱ-ㅎㅏ-ㅣ가-힣a-zA-Z0-9/\\s/g]", "");
				strDetail = strDetail.replaceAll("서비스 기사   기사본문 필요한 부분을 치환  ", "");
				strDetail = strDetail.replaceAll("앵커", "");

				//기자 이름
				String reporter = document.getElementsByClass("infomation").text().split(" ")[1];
				String strRepoter = reporter;
//                    strRepoter = strRepoter.replaceAll("기자", "");

				//기자 이메일
				String email = document.select("li.clear > a").get(2).text();

				//언론사
				String media = document.select("div.aht-wrapper > div > a > img").attr("alt");

				//기사 이미지 URL
				String imgUrl = document.select("figure.photo-layout > img").attr("src");

                /* html() : 태그까지 가져오기
                   text() : 내용만 가져오기 */

//                    System.out.println("newsNo : " + newsNo);
////                System.out.println("상품 이미지 URL : " + imageUrl.attr("abs:src"));
//                    System.out.println("기사 제목 : " + title.text());
//                    System.out.println("기사 날짜 : " + date.text());
//                    System.out.println("본문 : " + strDetail);
//                    System.out.println("기자 : " + strRepoter);
//                    System.out.println("이메일 : " + email.text());
////                for (int infoIdx = 0; infoIdx < info.size(); infoIdx++) {
////                    System.out.println(productInfo[infoIdx] + info.get(infoIdx).text());
////                }
//                    System.out.println("---------------------------");

				//DB에 collection 에 접근 하기 위한 VO 객체
				Book book = new Book();
				book.setNewsNo(newsNo);
				book.setTitle(strNTT);
				book.setDate(date);
				book.setDetail(strDetail);
				book.setReporter(strRepoter);
				book.setEmail(email);
				book.setMedia(media);
				book.setImg(imgUrl);

				//DB에 연결하기 위한 Repository
                    bookRepository.insert(book);

				// db 값 update
				// id 값 필요 (setId)
//                    bookRepository.save(book);

				newsNoByGangwonNewspaper += 1;
				crawlingEntity.setMedia("newsByGangwonNewspaper");
				crawlingEntity.setNewsNo(newsNoByGangwonNewspaper);
				crawlingRepository.save(crawlingEntity);


			} catch (NullPointerException nullPointerException) {
				newsNoByGangwonNewspaper += 1;
				logger.info(nullPointerException.toString());
				crawlingEntity.setMedia("newsByGangwonNewspaper");
				crawlingEntity.setNewsNo(newsNoByGangwonNewspaper);
				crawlingRepository.save(crawlingEntity);
				continue;

			} catch (Exception e) {
				logger.info(e.toString());
				System.out.println("여기 에러");
				newsNoByGangwonNewspaper += 1;
				crawlingEntity.setMedia("newsByGangwonNewspaper");
				crawlingEntity.setNewsNo(newsNoByGangwonNewspaper);
				crawlingRepository.save(crawlingEntity);
				continue;
			}
		}
		try {
			//DB 조회
			List<Map<String, Object>> list = new ArrayList<>();
//			for (Book books : bookRepository.findAll()) {
			for (long newsNo = firstNewsNoByGangwonNewspaper; newsNo < lastNewsNoByGangwonNewspaper+1; newsNo++) {
				try {
					Book books = bookRepository.findByNewsNo((long) newsNo);
					Map<String, Object> map = new HashMap<>();
//                System.out.println(book.getTitle().toString());
					map.put("newsNo", books.getNewsNo());
					map.put("title", books.getTitle().toString());
					map.put("date", books.getDate().toString());
					map.put("detail", books.getDetail().toString());
					map.put("reporter", books.getReporter().toString());
					map.put("email", books.getEmail().toString());
					map.put("media", books.getMedia());
					map.put("imgUrl", books.getImg());
					list.add(map);
				} catch (NullPointerException nullPointerException) {
					logger.info(nullPointerException.toString());
					continue;
				}
			}

			/*형태소 분석을 위한 Komoran*/
			Komoran komoran = new Komoran(DEFAULT_MODEL.FULL);

			/*사용자 사전 등록*/
			String userDicPath = "src/main/java/com/example/crawling/dictionary/dicUser.txt";
			komoran.setUserDic(userDicPath);

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
				for (int tokenIdx = 0; tokenIdx < tokenListTitle.size(); tokenIdx++) {
					String str = tokenListTitle.get(tokenIdx).getMorph().toString().replaceAll("[^\uAC00-\uD7A3a-zA-Z]", " ");
					tokenListTitle.get(tokenIdx).setMorph(str);

				}
				for (int tokenIdx = 0; tokenIdx < tokenListDetail.size(); tokenIdx++) {
					tokenListDetail.get(tokenIdx).setMorph(tokenListDetail.get(tokenIdx).getMorph().toString().replaceAll("[^\\uAC00-\\uD7A3a-zA-Z]", " "));
				}

				int nonIdxTitle = 0;    //tokenListTitle remove 한 후 다음 index 받기 위한 값
				int nonIdxDetail = 0;   //tokenListDetail remove 한 후 다음 index 받기 위한 값
				int tokenTitleSize = tokenListTitle.size();
				int tokenDetailSize = tokenListDetail.size();
				/*품사 NNG NNP 이외에 제거*/
				for (int idx = 0; idx < tokenTitleSize; idx++) {
					if (!tokenListTitle.get(nonIdxTitle).getPos().equals("NNP") && !tokenListTitle.get(nonIdxTitle).getPos().equals("NNG")) {   //NNG, NNP 가 아닌 모든 품사 제거
						tokenListTitle.remove(nonIdxTitle);
					} else {
						nonIdxTitle += 1;   //remove가 실행되지 않을 경우 다음 index로 체크
					}
				}
				for (int idx = 0; idx < tokenDetailSize; idx++) {
					if (!tokenListDetail.get(nonIdxDetail).getPos().equals("NNP") && !tokenListDetail.get(nonIdxDetail).getPos().equals("NNG")) {
						tokenListDetail.remove(nonIdxDetail);
					} else {
						nonIdxDetail += 1;
					}
				}
				/*품사 NNG NNP 이외에 제거*/


				//품사 태깅을 위한 작업
				//NNG: 일반명사, NNP: 고유명사
				String tokenTitleStr = "";
				String tokenDetailStr = "";
				for (int j = 0; j < tokenListTitle.size(); j++) {
					if (tokenListTitle.get(j).getPos().equals("NNP") || tokenListTitle.get(j).getPos().equals("NNG")) {
						tokenTitleStr += "\"" + tokenListTitle.get(j).getMorph() + "\"" + ", ";
					}
				}
				for (int j = 0; j < tokenListDetail.size(); j++) {
					if (tokenListDetail.get(j).getPos().equals("NNP") || tokenListDetail.get(j).getPos().equals("NNG")) {
//                        System.out.format("(%2d, %2d) %s/%s\n", token.getBeginIndex(), token.getEndIndex(), token.getMorph(), token.getPos());
						tokenDetailStr += "\"" + tokenListDetail.get(j).getMorph() + "\"" + ", ";
					}
				}

				//형태소 분석한 데이터를 넣기 위한 Collection 생성 및 접근 VO 객체
				Book book = new Book();
				book.setUrl(originData.get(i).get("url").toString());
				book.setTitle(originData.get(i).get("originTitle").toString());
				book.setDetail(originData.get(i).get("originDetail").toString());
				book.setNewsNo(Long.parseLong(list.get(i).get("newsNo").toString()));
				book.setNontagTitle(list.get(i).get("title").toString());
				book.setDate(list.get(i).get("date").toString());
				book.setNontagDetail(list.get(i).get("detail").toString());
				book.setReporter(list.get(i).get("reporter").toString());
				book.setEmail(list.get(i).get("email").toString());
				book.setAnalyzeTitle(tokenTitleStr);
				book.setAnalyzeDetail(tokenDetailStr);
				book.setNounTitle(tokenListTitle);
				book.setNounDetail(tokenListDetail);
				book.setCrawlDate(originData.get(i).get("regDate").toString());
				book.setMedia(list.get(i).get("media").toString());
				book.setImg(list.get(i).get("imgUrl").toString());

                    bookRepository.save(book);

				crawlingEntity.setMedia("newsByGangwonNewspaper");
				crawlingEntity.setNewsNo(newsNoByGangwonNewspaper);
				crawlingRepository.save(crawlingEntity);
			}
			logger.info("강원뉴스 크롤링 완료");
		} catch (Exception e) {
			logger.info(e.toString());
			e.printStackTrace();
		}

	}
}
