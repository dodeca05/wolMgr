package com.pro.WOLmgr.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(indexes = {@Index(name = "idx_email", columnList = "email")})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userNumber;
    @Column(nullable = false, length = 50,unique = true)
    private String userId;
    @Column(nullable = false, length = 80)
    private String password;
    @Column(nullable = false, length = 50)
    private String username;
    @Column(nullable = false, length = 100,unique = true)
    private String email;
    private Byte userAuthority;
}
