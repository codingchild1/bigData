package com.example.crawling.test;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Crawling {
    public static void main(String[] args) {
        String[] productInfo = new String[]{"모델번호 : ", "출시일 : ", "컬러 : ", "발매가 : "};

        //상품번호 10000번부터 10010번까지 스크래핑
        for (int productNum = 23632; productNum <= 23642; productNum++) {

            final String url = "https://kream.co.kr/products/"+productNum;

            try {
                Connection conn = Jsoup.connect(url);
                Document document = conn.get();
                //상품 이미지 URL
                Element imageUrl = document.getElementsByAttributeValue("alt", "상품 이미지").first();

                //브랜드
                Element brand = document.getElementsByClass("brand").first();

                //상품명
                Element title = document.getElementsByClass("sub_title").first();

                //상품정보
                Elements info = document.getElementsByClass("product_info");

                System.out.println("productNumber : " + productNum);
                System.out.println("상품 이미지 URL : " + imageUrl.attr("abs:src"));
                System.out.println("브랜드 : " + brand.text());
                System.out.println("상품명 : " + title.text());
                for (int infoIdx = 0; infoIdx < info.size(); infoIdx++) {
                    System.out.println(productInfo[infoIdx] + info.get(infoIdx).text());
                }
                System.out.println("---------------------------");
            } catch (Exception e) {
            }
        }
    }
}
