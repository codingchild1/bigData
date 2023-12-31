package com.example.crawling.dao;

import com.example.crawling.vo.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends MongoRepository<Book, String> {

	public List<Book> findAllByOrderByDateDesc();

	// get total row count
	public long count();

	// get total row count by title
	public long countByTitleRegexOrderByDateDesc(String title);

	// get total row count by content
	public long countByDetailRegexOrderByDateDesc(String detail);

	// get total row count by reporter
	public long countByReporterRegexOrderByDateDesc(String reporter);

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

	public Book findByNewsNo(long newsNo);
}

