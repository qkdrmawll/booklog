package com.booklog.domain.like.dao;

import com.booklog.domain.like.domain.Like;
import com.booklog.domain.log.domain.Log;
import com.booklog.domain.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Like,Long> {
    boolean existsByMemberAndLog(Member member, Log log);
    void deleteByMemberAndLog(Member member, Log log);
}
