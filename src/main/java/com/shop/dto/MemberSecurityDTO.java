package com.shop.dto;

import com.shop.constant.Role;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Map;

@Getter
@Setter
@ToString
<<<<<<< HEAD
public class MemberSecurityDto extends User implements OAuth2User {
=======
public class MemberSecurityDTO extends User implements OAuth2User {
>>>>>>> ae5dfb45d6fe8b4c40c1ed855fad6400cbac2ae8
    private String id;
    private String email;
    private String password;
    private String address;
    private boolean social;
    private Role role;
    private Map<String, Object> attr;   // 소설 로그인 정보
<<<<<<< HEAD
    public MemberSecurityDto(String username, String password, String email,
=======
    public MemberSecurityDTO(String username, String password, String email,
>>>>>>> ae5dfb45d6fe8b4c40c1ed855fad6400cbac2ae8
                             boolean social,
                             Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);

        this.id = username;
        this.password = password;
        this.email = email;
        this.social = social;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return this.getAttr();
    }

    @Override
    public String getName() {
        return this.id;
    }
}
