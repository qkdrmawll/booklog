package com.booklog.domain.follow.application;

import com.booklog.domain.follow.dao.FollowRepository;
import com.booklog.domain.follow.domain.Follow;
import com.booklog.domain.member.Member;
import com.booklog.domain.member.MemberRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
@Transactional
public class FollowService {
    private final FollowRepository followRepository;
    private final MemberRepository memberRepository;
    public void follow(Member follower, Member followed) {
        Follow follow = Follow.builder()
                .follower(follower)
                .followed(followed)
                .build();
        followRepository.save(follow);
    }

    public List<Member> getFollowingList(Member member) {
        List<Follow> followList = followRepository.findAllByFollower(member);
        List<Member> followingList = new ArrayList<>();
        for (Follow follow : followList) {
            Member following = memberRepository.findById(follow.getFollowed().getId()).orElseThrow();
            followingList.add(following);
        }
        return followingList;
    }


}
