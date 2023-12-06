package com.example.crawling.dao;

import com.example.crawling.vo.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends MongoRepository<Book, String> {

	// get total row count
	public long count();

	// get total row count by title
	public long countByTitleRegex(String title);

	// get total row count by content
	public long countByDetailRegex(String detail);

	// get total row count by reporter
	public long countByReporterRegex(String reporter);

	// get latest news
	public List<Book> findFirstByOrderByDateDesc();

    public Book findByTitle(String title);
    public List<Book> findByDetail(String detail);
//    Book save(Book entity);   //repository 로 mongodb insert (update) 하기

//	public List<Book> findByTitleRegex(String title);
	public Page<Book> findByTitleRegex(String title, Pageable pageable);
	public Page<Book> findByDetailRegex(String detail, Pageable pageable);
	public Page<Book> findByReporterRegex(String reporter, Pageable pageable);

	public Book findByUrl(String newsUrl);


}

