package com.booklog.domain.like.application;

import com.booklog.domain.like.dao.LikeRepository;
import com.booklog.domain.like.domain.Like;
import com.booklog.domain.like.dto.LikeDto;
import com.booklog.domain.log.domain.Log;
import com.booklog.domain.member.Member;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
@Transactional
public class LikeService {
    private final LikeRepository likeRepository;

    public long like(Member member, Log log) {
        if (likeRepository.existsByMemberAndLog(member, log)) { // 이미 like가 있던 경우
            log.subtractLike();
            likeRepository.deleteByMemberAndLog(member, log);
            return log.getLikesCount();
        }
        // like 새로 생성하는 경우
        log.addLike();
        Like like = Like.builder().member(member).log(log).build();
        likeRepository.save(like);
        return log.getLikesCount();
    }
}
