package com.booklog.domain.follow.application;

import com.booklog.domain.follow.dao.FollowRepository;
import com.booklog.domain.member.Member;
import com.booklog.domain.member.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class FollowServiceTest {

    @Autowired
    FollowService followService;
    @Autowired
    FollowRepository followRepository;
    @Autowired
    MemberRepository memberRepository;

    Member createMember (String name) {
        Member member = Member.builder()
                .name(name)
                .email("test")
                .nickname("test")
                .build();
        memberRepository.save(member);
        return member;
    }

    @Test
    @DisplayName("팔로우 생성 테스트")
    void follow() {
        Member follower = createMember("follower");
        Member followed = createMember("followed");

        followService.follow(follower, followed);
        Assertions.assertThat(followRepository.existsByFollowerAndFollowed(follower,followed)).isTrue();
    }
    @Test
    @DisplayName("팔로잉 카운트 테스트")
    void following_count() {
        Member follower = createMember("follower");
        Member followed = createMember("followed");

        followService.follow(follower, followed);

        Assertions.assertThat(followRepository.countByFollowed(followed)).isEqualTo(1);
    }
    @Test
    @DisplayName("팔로워 카운트 테스트")
    void follower_count() {
        Member follower = createMember("follower");
        Member followed = createMember("followed");
        Member followed2 = createMember("followed");

        followService.follow(follower, followed);
        followService.follow(follower, followed2);

        Assertions.assertThat(followRepository.countByFollower(follower)).isEqualTo(2);
    }

    @Test
    @DisplayName("팔로잉 리스트 테스트")
    void getFollowingList() {
        Member follower = createMember("follower");
        Member followed = createMember("followed");
        Member followed2 = createMember("followed");

        followService.follow(follower, followed);
        followService.follow(follower, followed2);
        List<Member> list = followService.getFollowingList(follower);

        Assertions.assertThat(list.size()).isEqualTo(2);

    }
}