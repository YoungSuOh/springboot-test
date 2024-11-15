package org.example.springjpa;

import org.example.springjpa.domain.board.dao.BoardDAO;
import org.example.springjpa.domain.board.entity.BoardEntity;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.List;

// 내장 DB 가 H2 를 수행하지 않겠다
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)

// @DataJpaTest 를 사용하면 자동으로 EmbededDatabase-H2 를 사용하게 됨
// MySQL 과 같이 외부의 DB 를 연결하련느 경우엔 이 어노테이션 사용
@DataJpaTest

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class JPA_BoardRepositoryTest {

    @Autowired
    private BoardDAO boardDAO;

    @BeforeEach
    public void solid(){
        System.out.println("-".repeat(83));
    }

    @AfterEach
    public void solid2() {
        System.out.print("// ");
        System.out.println("-".repeat(80));
    }

    @Test
    public void list1() {
        List<BoardEntity> list = boardDAO.findAll();
        System.out.println("*********** list1 ***********");
        list.stream().forEach(System.out::println);
    }

    @Test
    public void list2() {
        List<BoardEntity> list = boardDAO.findAll(Sort.by("name").ascending());
        System.out.println("*********** list2 ***********");
        list.stream().forEach(System.out::println);
    }

    @Test
    public void list3() {
        List<BoardEntity> list = boardDAO.findAll(Sort.by("name").descending());
        System.out.println("*********** list3 ***********");
        list.stream().forEach(System.out::println);
    }

    @Test
    public void list4() {
        Page<BoardEntity> list = boardDAO.findAll(PageRequest.of(0, 3));
        System.out.println("*********** list4 ***********");
        list.stream().forEach(System.out::println);
    }

    @Test
    public void list5() {
        Page<BoardEntity> list = boardDAO.findAll(PageRequest.of(1, 3));
        System.out.println("*********** list5 ***********");
        list.stream().forEach(System.out::println);
    }

    @Test
    public void list6() {
        Page<BoardEntity> list = boardDAO.findAll(PageRequest.of(0, 3, Sort.by("name")));
        System.out.println("*********** list6 ***********");
        list.stream().forEach(System.out::println);
    }



}
