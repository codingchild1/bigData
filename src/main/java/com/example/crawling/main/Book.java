package com.example.crawling.main;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "test")
public class Book {
    @Id
    private int newsNo;
    private String title;
    private String date;
    private String detail;
    private String reporter;
    private String email;
    private String test;

}
