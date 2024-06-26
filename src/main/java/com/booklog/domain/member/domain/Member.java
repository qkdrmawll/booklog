package com.booklog.domain.member.domain;

import com.booklog.BaseEntity;
import com.booklog.domain.log.domain.Log;
import com.booklog.domain.member.oauth.SessionUser;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Entity
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 20)
    private String name;

    @Column(length = 20)
    private String nickname;

    @Column(nullable = false)
    private String email;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "member")
    private List<Log> logs = new ArrayList<>();

    @Builder
    public Member(String name, String nickname, String email) {
        this.name = name;
        this.nickname = nickname;
        this.email = email;
    }

    public void update(String nickname, String email){
        this.nickname = nickname;
        this.email = email;
    }

    public void addLog(Log log) {
        this.logs.add(log);
    }

    public String getRoleKey() {
        return this.role.getKey();
    }
}
