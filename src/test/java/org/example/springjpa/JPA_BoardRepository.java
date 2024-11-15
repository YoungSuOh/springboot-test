package org.example.springjpa;


import jakarta.transaction.Transactional;
import org.example.springjpa.domain.board.dao.BoardDAO;
import org.example.springjpa.domain.board.entity.BoardEntity;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)

// @DataJpaTest 를 사용하면 자동으로 EmbededDatabase-H2 를 사용하게 됨
// MySQL 과 같이 외부의 DB 를 연결하련느 경우엔 이 어노테이션 사용
@DataJpaTest

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class JPA_BoardRepository {
    @Autowired
    private BoardDAO boardDAO;

/*

    @Test
    @Order(1) // 실행 순서를 정한다. 숫자가 작을 수록 먼저 실행된다.
    @Rollback(false)  // rollback이 기본이다. DML문 수행한 후에는
    public void insert(){
        BoardEntity boardEntity = new BoardEntity();
        boardEntity.setId("banana");
        boardEntity.setName("바나나");
        boardEntity.setSubject("노란 바나나");
        boardEntity.setContent("예전에는 바나나가 너무 비쌈");

        boardDAO.save(boardEntity);
    }

    @Test
    @Order(1) // 실행 순서를 정한다. 숫자가 작을 수록 먼저 실행된다.
    @Rollback(false)  // rollback이 기본이다. DML문 수행한 후에는
    @Transactional
    public void insert2(){
        BoardEntity boardEntity = new BoardEntity();
        boardEntity.setId("strawberry");
        boardEntity.setName("딸기");
        boardEntity.setSubject("빨간 딸기");
        boardEntity.setContent("딸기가 좋아");

        boardDAO.save(boardEntity);
    }
*/

    @Test
    @Order(2)
    public void list(){
        List<BoardEntity> list=boardDAO.findAll();
        list.stream().forEach(System.out::println);
    }

    // 쿼리 메서드
    @Test
    @Order(3)
    public void bySeq(){
        BoardEntity boardEntity = (BoardEntity) boardDAO.findBySeq(7);
        System.out.println(boardEntity);
    }

}
