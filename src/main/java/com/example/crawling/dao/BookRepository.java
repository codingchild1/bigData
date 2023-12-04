package com.example.crawling.dao;

import com.example.crawling.vo.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends MongoRepository<Book, String> {
    public Book findByTitle(String title);
    public List<Book> findByDetail(String detail);
//    Book save(Book entity);   //repository 로 mongodb insert (update) 하기

//	public List<Book> findByTitleRegex(String title);
	public List<Book> findByTitleRegex(String title);
	public Page<Book> findByDetailRegex(String detail, Pageable pageable);
	public List<Book> findByReporterRegex(String reporter);

}
