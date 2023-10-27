package com.example.crawling.dto;

import com.example.crawling.vo.Board;
import lombok.Data;

@Data
public class BoardSaveDto {
    private String title;
    private String content;

    public Board toEntity() {
        Board board = new Board();
        board.setTitle(title);
        board.setContent(content);
        return board;
    }
}