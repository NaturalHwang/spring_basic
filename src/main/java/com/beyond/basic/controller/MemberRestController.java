package com.beyond.basic.controller;

import com.beyond.basic.domain.*;
import com.beyond.basic.repository.MemberRepository;
import com.beyond.basic.service.MemberService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
// RestController의 경우 모든 메서드 상단에 @ResponseBody 붙는 효과 발생
@RequestMapping("/rest")
@Api(tags = "회원관리서비스")
// http://localhost:8080/swagger-ui/#/ <<여기로 접속
public class MemberRestController {
    private final MemberService memberService;
    private final MemberRepository memberRepository;

    @Autowired
    public MemberRestController(MemberService memberService, MemberRepository memberRepository){
        this.memberService = memberService;
        this.memberRepository = memberRepository;
    }

    @GetMapping("/member/text")
    public String memberText(){
        return "ok";
    }

    @GetMapping("/member/list")
//    public List<MemberResDto> memberList(){
//        return memberService.memberList();
//    }
    public ResponseEntity<Object> memberList(){
        List<MemberResDto> memberList = memberService.memberList();
        CommonResDto commonResDto = new CommonResDto(HttpStatus.OK, "members find successful", memberList);
        return new ResponseEntity<>(commonResDto, HttpStatus.OK);
    }

    @GetMapping("/member/detail/{id}")
//    public MemberDetailResDto memberDetail(@PathVariable Long id){
//        return memberService.memberDetail(id);
//    }
    public ResponseEntity<Object> memberDetail(@PathVariable Long id){
//        try {
//            MemberDetailResDto member = memberService.memberDetail(id);
//
//            CommonResDto commonResDto = new CommonResDto(HttpStatus.OK, "member find successful", member);
//            return new ResponseEntity<>(commonResDto, HttpStatus.OK);
//        } catch (EntityNotFoundException e){
//            CommonErrorDto commonErrorDto = new CommonErrorDto(HttpStatus.NOT_FOUND.value(), e.getMessage());
//            return new ResponseEntity<>(commonErrorDto, HttpStatus.NOT_FOUND);
////            return new ResponseEntity<>(
////            new CommonErrorDto(HttpStatus.NOT_FOUND.value(), e.getMessage()),HttpStatus.NOT_FOUND);
//        }
        MemberDetailResDto member = memberService.memberDetail(id);
//
        CommonResDto commonResDto = new CommonResDto(HttpStatus.OK, "member find successful", member);
        return new ResponseEntity<>(commonResDto, HttpStatus.OK);
    }

    @PostMapping("/member/create")
//    public String createMember(@RequestBody MemberReqDto dto){
//        try {
//            memberService.memberCreate(dto);
//            return "ok";
//        } catch (IllegalArgumentException e){
//            e.printStackTrace();
//            return "error!!";
//        }
//    }
    public ResponseEntity<Object> createMember(@RequestBody MemberReqDto dto){
//        try {
//            memberService.memberCreate(dto);
//            CommonResDto commonResDto = new CommonResDto(HttpStatus.CREATED, "Create successful", dto);
//            return new ResponseEntity<>(commonResDto, HttpStatus.CREATED);
//        } catch (IllegalArgumentException e){
//            CommonErrorDto commonErrorDto = new CommonErrorDto(HttpStatus.BAD_REQUEST.value(), e.getMessage());
//            return new ResponseEntity<>(commonErrorDto, HttpStatus.BAD_REQUEST);
//        }
            memberService.memberCreate(dto);
            CommonResDto commonResDto = new CommonResDto(HttpStatus.CREATED, "Create successful", dto);
            return new ResponseEntity<>(commonResDto, HttpStatus.CREATED);
    }

//    수정은 2가지 요청 방식: PUT, PATCH
//    PATCH 요청은 부분 수정, PUT 요청은 덮어쓰기
//    어떤 mapping을 붙여도 상관은 없으나 통신 규약에 적절한 매칭을 해주는 것이 좋음(ex. getMapping에 삭제 요청 가능).
//    @PutMapping
    @PatchMapping("/member/pw/update")
    public String memberList(@RequestBody MemberUpdateDto dto){
        memberService.pwUpdate(dto);
        return "ok";
    }

//    @DeleteMapping("/member/delete/{id}&{password}")
    @DeleteMapping("/member/delete/{id}")
    public String deleteMember(@PathVariable Long id/*,@PathVariable String password*/){
//        memberService.deleteMember(id, password);
        memberService.deleteMember(id);
        return "ok";
    }

//    lazy(지연 로딩), eager(즉시 로딩) 테스트
    @GetMapping("/member/post/all")
    @ResponseBody
    public void memberPostAll(){
        List<Member> memberList = memberRepository.findAll();
        for(Member m : memberList){
            System.out.println(m.getPosts().size());
        }
    }
}
