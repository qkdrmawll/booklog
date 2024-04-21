package com.booklog.domain.follow.application;

import com.booklog.domain.follow.dao.FollowRepository;
import com.booklog.domain.follow.domain.Follow;
import com.booklog.domain.member.domain.Member;
import com.booklog.domain.member.dao.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
@Transactional
@Slf4j
public class FollowService {
    private final FollowRepository followRepository;
    private final MemberRepository memberRepository;
    public void follow(Member follower, Member followed) {
        Follow follow = Follow.builder()
                .follower(follower)
                .followed(followed)
                .build();
        log.info(follower+ "님이 "+ followed +"님을 팔로우했습니다.");
        followRepository.save(follow);
    }

    public void unfollow(Member follower, Member followed) {
        if (followRepository.existsByFollowerAndFollowed(follower,followed)) {
            followRepository.deleteByFollowerAndFollowed(follower,followed);
        }
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

    public boolean isFollow(Member loggedInMember, Member targetMember) {
        return followRepository.existsByFollowerAndFollowed(loggedInMember,targetMember);
    }


}
