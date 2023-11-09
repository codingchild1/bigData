package com.example.crawling.main;

import com.example.crawling.vo.Book;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends MongoRepository<Book, String> {
    public Book findByTitle(String title);
    public List<Book> findByDetail(String detail);
}
