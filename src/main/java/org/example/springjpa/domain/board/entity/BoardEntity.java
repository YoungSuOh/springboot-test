package org.example.springjpa.domain.board.entity;


import jakarta.persistence.*;
import lombok.Data;
import org.example.springjpa.domain.users.entity.Users;
import org.example.springjpa.global.entity.BaseEntity;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name="board")
public class BoardEntity {
    @Id // PK 설정
    @Column(name="seq")
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto Increment 생성
    // 특정 데이터베이스에 맞게 자동으로 생성; 자동으로 시퀀스 테이블 생성
    // @GeneratedValue(strategy = GenerationType.SEQENCE, generator="BOARD_SEQ-GENERATOR")
    private Long seq;

    @Column(name = "id", nullable = false, unique = true, length = 30)
    private String id;

    @Column(name = "name", nullable = false, length = 30)
    private String name;

    @Column(name = "subject")
    private String subject;

    @Column(name = "content")
    private String content;

    // private Timestamp logtime;
    // @CreationTimestamp // 엔티티가 생성되는 시점의 시간 등록 - insert 할 때 자동으로 시간 등록
    @UpdateTimestamp        // update 할 때 자동으로 시간 등록
    private LocalDateTime logtime = LocalDateTime.now();

}
