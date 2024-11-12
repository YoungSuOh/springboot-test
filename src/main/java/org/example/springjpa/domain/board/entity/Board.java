package org.example.springjpa.domain.board.entity;


import jakarta.persistence.*;
import lombok.Data;
import org.example.springjpa.domain.users.entity.Users;
import org.example.springjpa.global.entity.BaseEntity;

@Entity
@Data
@Table(name = "Board") // 엔티티 테이블 이름 지정 필수 !! 나중에 개고생 할 수도 있어요 ㅠ.ㅠ
public class Board extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "authorId", referencedColumnName = "id")
    private Users authorId;

    @Column(name = "name", nullable = false, length = 30)
    private String name;

    @Column(name = "subject")
    private String subject;

    @Column(name = "content")
    private String content;

}
