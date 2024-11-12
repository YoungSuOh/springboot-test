package org.example.springjpa.domain.users.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.example.springjpa.global.entity.BaseEntity;

@Entity
@Table(name = "Users") // 엔티티 테이블 이름 지정 필수 !!
@Data
public class Users extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String email;
}
