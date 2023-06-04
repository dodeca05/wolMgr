package com.pro.WOLmgr.entity;

import com.pro.WOLmgr.dto.UserInfoDTO;
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
public class UserEntity {
    @Id
    private String username; // 사용자 이름을 나타내는 필드, 디폴트 255

    @Column(nullable = false, unique = true)
    private String userId; // 사용자 ID를 나타내는 필드, null 값 불가 및 고유한 값이어야 함, 디폴트 255

    private String password; // 사용자 비밀번호를 나타내는 필드, 디폴트 255

    @Column(nullable = false, unique = true)
    private String email; // 사용자 이메일을 나타내는 필드, null 값 불가 및 고유한 값이어야 함, 디폴트 255

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER) // Enum 타입의 컬렉션으로 저장되며, 즉시 로딩됨
    @Enumerated(EnumType.STRING) // 그걸 문자열로 저장함
    private Set<Role> roles; // 사용자의 역할 정보를 나타내는 필드

    @OneToMany(mappedBy = "authUser", cascade = CascadeType.ALL)
    private List<DeviceAuthEntity> deviceAuthEntity;

    @ElementCollection
    @CollectionTable(name = "user_tokens", joinColumns = @JoinColumn(name = "username"))
    private List<String> token;

    public List<String> getRoleList() {
        List<String> roleList = new ArrayList<>();
        for (Role role : roles) roleList.add(role.name());
        return roleList;
    }

    public void change(UserInfoDTO dto){
        this.username = dto.getUsername();
        this.userId = dto.getUserId();
        this.email = dto.getEmail();
        this.roles = dto.getRoles();
    }

    public UserInfoDTO toDto(){
        return UserInfoDTO
                .builder()
                .userId(userId)
                .username(username)
                .email(email)
                .roles(roles)
                .build();
    }
}
