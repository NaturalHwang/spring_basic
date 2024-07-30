package com.beyond.basic.repository;

import com.beyond.basic.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
//MemberRepository가 되기 위해서 JpaRepository를 상속해야 하고, 상속 시에 Entity명과 entity의 pk타입을 명시
//MemberRepository는 JpaRepository를 상속함으로서 JpaRepository의 주요 기능을 상속
//JpaRepository가 인터페이스임에도 해당 기능을 상속해서 사용할 수 있는 이유는 hibernate 구현체가 미리 구현돼 있기때문.
//런타임 시점에 사용자가 인터페이스에 정의한 메서드를 /*프록시(대리인) 객체가*/ 리플렉션 기술을 통해 메서드를 구현
public interface MemberRepository extends /*OldMemberRepository,*/ JpaRepository<Member, Long> {
//    런타임 시점에 사용자가 정의한 메서드도 구현함(개쩐다)
    Optional<Member> findByEmail(String email);
//    List or Optional 반환 타입을 추론해서 사용할 것.(반환 값이 여러개일 때 최상단 하나만 나올지, 에러일지?)
//    List<Member> findByName(String name);
}