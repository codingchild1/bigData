package com.example.crawling.main;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface BookRepository extends MongoRepository<Book, String> {
}
