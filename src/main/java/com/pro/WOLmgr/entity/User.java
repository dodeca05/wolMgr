package com.pro.WOLmgr.entity;

import com.pro.WOLmgr.util.Role;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userNumber; // pk로 사용함, 자동으로 생성되는 ID 값

    @Column(nullable = false, unique = true)
    private String userId; // 사용자 ID를 나타내는 필드, null 값 불가 및 고유한 값이어야 함, 디폴트 255

    private String password; // 사용자 비밀번호를 나타내는 필드, 디폴트 255

    private String username; // 사용자 이름을 나타내는 필드, 디폴트 255

    @Column(nullable = false, unique = true)
    private String email; // 사용자 이메일을 나타내는 필드, null 값 불가 및 고유한 값이어야 함, 디폴트 255

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER) // Enum 타입의 컬렉션으로 저장되며, 즉시 로딩됨
    @Enumerated(EnumType.STRING) // 그걸 문자열로 저장함
    private Set<Role> roles; // 사용자의 역할 정보를 나타내는 필드

    public List<String> getRoleList() {
        List<String> roleList = new ArrayList<>();
        for (Role role : roles) {
            roleList.add(role.name());
        }
        return roleList;
    }
}
