package com.booklog.member;

import com.booklog.BaseEntity;
import com.booklog.log.Log;
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
    private String name;
    private String nickname;
    private String email;

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
}
