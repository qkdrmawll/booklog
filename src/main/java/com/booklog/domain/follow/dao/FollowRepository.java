package com.booklog.domain.follow.dao;

import com.booklog.domain.follow.domain.Follow;
import com.booklog.domain.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FollowRepository extends JpaRepository<Follow, Long> {
    boolean existsByFollowerAndFollowed(Member follower, Member followed);
    void deleteByFollowerAndFollowed(Member follower, Member followed);
    long countByFollower(Member follower);
    long countByFollowed(Member followed);
    List<Follow> findAllByFollower(Member member);
    List<Follow> findAllByFollowed(Member member);
}
