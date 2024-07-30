//package com.beyond.basic.repository;
//
//import com.beyond.basic.domain.Member;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;
//
//import javax.persistence.EntityManager;
//import java.util.List;
//import java.util.Optional;
//
//@Repository
//public class MemberJpaRepository implements OldMemberRepository{
//    @Autowired // EntityManager JPA의 핵심 클래스(객체)
//// Entity의 생명 주기를 관리, 데이터베이스와의 모든 인터페이싱 책임
////    즉, 엔티티 대상으로 CRUD 기능 제공
//    private EntityManager entityManager;
//
//    @Override
//
//    public Member save(Member member) {
////persist: 전달된 엔티티(Member)가 EntityManager의 관리상태가 되도록 만들어주고, 트랜잭션이 커밋될 때 DB에 저장.(insert)
//        entityManager.persist(member);
//        return null;
//    }
//
//    @Override
//    public List<Member> findAll() {
////        jpql: jpa의 raw쿼리 문법(객체 지향 쿼리 문법)
////        jpa에서는 jpql 문법 컴파일 에러가 나오지 않으나, springdatajpa에서는 컴파일 에러 발생
//        List<Member> memberList = entityManager.createQuery("select m from Member m", Member.class).getResultList();
//        return memberList;
//    }
//
//    @Override
//    public Optional<Member> findById(long id) {
////        entitymanager를 통해 find(return 타입 클래스 지정 및 매개변수로 pk 필요)
//        Member member = entityManager.find(Member.class, id);
//        return Optional.ofNullable(member);
//    }
////    pk 이외의 컬럼으로 조회할 때
////    jpql 문법으로 raw쿼리 비슷하게 직접 쿼리 작성.
////    public Member findByEmail(String email){
////        Member member = entityManager
////                .createQuery("select m from Member m where m.email = :email", Member.class)
////                .setParameter("email", email).getSingleResult();
////        return member;
////    }
//}
