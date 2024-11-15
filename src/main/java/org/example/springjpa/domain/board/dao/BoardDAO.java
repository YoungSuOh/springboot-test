package org.example.springjpa.domain.board.dao;

import org.example.springjpa.domain.board.entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Dictionary;

@Repository // ID : Primary Key Type
public interface BoardDAO extends JpaRepository<BoardEntity, Long> {

    public Object findBySeq(int seq);
}