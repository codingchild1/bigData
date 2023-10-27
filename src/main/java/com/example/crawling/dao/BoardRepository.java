package com.example.crawling.dao;

import com.example.crawling.vo.Board;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableMongoRepositories(basePackageClasses = com.example.crawling.dao.BoardRepository.class)
public interface BoardRepository extends MongoRepository<Board, String> {
    public List<Board> findFirstBy(String firstName);
}
