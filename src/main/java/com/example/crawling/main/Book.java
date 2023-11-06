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

    public Book() {}

    public Book( String title, String date, String detail, String reporter, String email) {
        this.title = title;
        this.date = date;
        this.detail = detail;
        this.reporter = reporter;
        this.email = email;
    }

    @Override
    public String toString() {
        return String.format(
                "Book[newsNo=%s, title='%s', date='%s', detail='%s', reporter='%s', email='%s']",
                newsNo, title, date, detail, reporter, email);

    }
}
