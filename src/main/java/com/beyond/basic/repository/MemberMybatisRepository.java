package com.beyond.basic.repository;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
// 해당 레파지토리를 mybatis 기술을 쓰겠다는 표현
@Mapper
public interface MemberMybatisRepository extends OldMemberRepository{ // extends 하면 안에 객체 사용할 수 있어
                                                                   // 아래 선언할 필요 없음
//    List<Member> findAll();
//
//    Member save();
//
//    Member findById(Long id);
}
