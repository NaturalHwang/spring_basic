package com.beyond.basic.service;

import com.beyond.basic.domain.*;
import com.beyond.basic.repository.MyMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service // service 계층임을 표현함과 동시에 싱글톤 객체로 생성
//        input값의 검증 및 실질적인 비지니스 로직은 서비스 계층에서 수행

//Transactional 어노테이션을 통해 모든 메서드에 트랜젝션을 적용하고, 만약 예외가 발생 시 롤백처리 자동화
@Transactional(readOnly = true) //=> 조회 성능 향상
//@Transactional // 각 메서드마다 하나의 트랜잭션(여러 쿼리를 하나의 작업으로 묶어서 하나라도 취소되면 롤백)으로 묶는다는 뜻
public class MemberService {
    private final MyMemberRepository memberRepository;

    @Autowired // Singleton 객체를 주입(DI - dependency injection) 받는다라는 것을 의미
    public MemberService(MyMemberRepository memoryRepository){
        this.memberRepository = memoryRepository;
    }

//    @Autowired
//    private MemberController memberController;
    @Transactional
    public void memberCreate(MemberReqDto dto) {
        if(dto.getPassword().length() < 8){
            throw new IllegalArgumentException("비밀번호가 너무 짧습니다");
        }
        if(memberRepository.findByEmail(dto.getEmail()).isPresent()){
            throw new IllegalArgumentException("이미 존재하는 이메일입니다.");
        }
//        Member member = new Member();
//        member.setName(dto.getName());
//        member.setEmail(dto.getEmail());
//        member.setPassword(dto.getPassword());
        Member member = dto.toEntity();
        memberRepository.save(member);
//        try{
//            memberRepository.save(member);
//        } catch (SQLIntegrityConstraintViolationException e){
//            throw new IllegalAccessException("이미 존재하는 이메일입니다.");
//        }
//        Transactional 롤백처리 테스트
//        if(member.getName().equals("kim")){
//            throw new IllegalArgumentException("잘못된 입력입니다.");
//        }
    }
    public MemberDetailResDto memberDetail(Long id){
//        MemberDetailResDto memberDetailResDto = new MemberDetailResDto();
        Optional<Member> optMember = memberRepository.findById(id);
//        클라이언트에게 적절한 예외 메세지와 상태 코드를 주는 것이 주요 목적
//        또한, 예외를 강제 발생시킴으로서 적절한 롤백 처리 하는 것도 주요 목적
        Member member = optMember.orElseThrow(() -> new EntityNotFoundException("없는 회원입니다."));
//        memberDetailResDto.setId(member.getId());
//        memberDetailResDto.setName(member.getName());
//        memberDetailResDto.setEmail(member.getEmail());
//        memberDetailResDto.setPassword(member.getPassword());
//        LocalDateTime createdTime = member.getCreatedTime();
//        if(createdTime!=null){
//          NPE 방지를 위해 넣어주는게 좋음.
//        }
//        getMonth : 영어 표기(ex. July), getMonthValue : 숫자 표기
//        String value = createdTime.getYear() + "년 " + createdTime.getMonthValue() + "월 " + createdTime.getDayOfMonth() + "일";
//        memberDetailResDto.setCreatedTime(value);
//        memberDetailResDto = member.detFromEntity();
//        return memberDetailResDto;
//        System.out.println("글쓴이가 쓴 글의 개수: " + member.getPosts().size());
//        for(Post p : member.getPosts()){
//            System.out.println("글의 제목: " + p.getTitle());
//        }
        return member.detFromEntity();
//          return memberDetailResDto;
    }

    public List<MemberResDto> memberList(){
        List<MemberResDto> memberResDtos = new ArrayList<>();
        List<Member> memberList = memberRepository.findAll();
//        for(int i = 0; i < memberList.size(); i++){
//            MemberResDto resDto = new Member().listFromEntity();
//            memberResDtos.add(resDto);
//        }
        for(Member member : memberList){
            memberResDtos.add(member.listFromEntity());
        }
//        for(int i = 0; i < memberList.size(); i++){
//            MemberResDto resDto = new MemberResDto();
//            resDto.setName(memberList.get(i).getName());
//            resDto.setEmail(memberList.get(i).getEmail());
//        }
//        for(Member member : memberList){
//            MemberResDto resDto = new MemberResDto();
//            resDto.setId(member.getId());
//            resDto.setName(member.getName());
//            resDto.setEmail(member.getEmail());
//            memberResDtos.add(resDto);
//        }

        return memberResDtos;
    }

    @Transactional
    public void pwUpdate(MemberUpdateDto dto){
        Member member = memberRepository.findById(dto.getId()).orElseThrow(() ->
                                        new EntityNotFoundException("member is not found"));
        member.updatePw(dto.getPassword());

//      기존 객체를 조회 후 수정한 다음에 save 시에는 jpa가 update 실행
        memberRepository.save(member);
    }

    @Transactional
    public void deleteMember(Long id /*, String password*/){
        Member member = memberRepository.findById(id).orElseThrow
                    (()-> new EntityNotFoundException("member is not found"));
//        if(!member.getPassword().equals(password)){
//            throw new IllegalArgumentException("잘못된 접근입니다");
//        } else {
////            member.updateDelYn("Y");
////            memberRepository.save(member);
            memberRepository.delete(member); // 완전 삭제
//        }
    }
}
