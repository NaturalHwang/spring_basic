package com.beyond.basic.repository;

import com.beyond.basic.domain.Member;

import java.util.List;
import java.util.Optional;

public interface OldMemberRepository {
//    return 타입 Member도 가능함(상황에 따라서 다르게 설정)
    Member save(Member member);

    List<Member> findAll();

    Optional<Member> findById(long id);
}
