package org.example.springjpa.domain.board.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component @Data
public class BoardDTO {
    private int seq;
    private String id;
    private String name;
    private String subject;
    private String content;
    private LocalDateTime logtime;
}
