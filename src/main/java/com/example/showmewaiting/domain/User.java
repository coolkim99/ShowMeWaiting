package com.example.showmewaiting.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "users")
@Getter @Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User implements UserDetails {

    @Id @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    private String name;

    @Column(name = "password")
    private String password;

    @Enumerated(EnumType.STRING)
    private UserType type; //STORE, CONSUMER

    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    private List<String> roles = new ArrayList<>();

    //양방향 연관관계가 있다면 한쪽은 JsonIgnore을 해줘야함
    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Order> orders = new ArrayList<>();


    @Builder
    public User(String email, String password, UserType type, String name, String auth) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.type = type;
    }

    public User(String subject, String s, Collection<? extends GrantedAuthority> authorities) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.type = type;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
