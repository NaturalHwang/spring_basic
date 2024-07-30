package com.beyond.basic.controller;

import com.beyond.basic.domain.MemberDetailResDto;
import com.beyond.basic.domain.MemberReqDto;
import com.beyond.basic.domain.MemberResDto;
import com.beyond.basic.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

//@RequiredArgsConstructor
@Controller // 싱글톤
public class MemberController {
//    의존성 주입(DI)방법 1. 생성자 주입 방식(가장 많이 사용하는 방식)
//    생성자가 1개밖에 없을 때는 Autowired 생략 가능
//    장점: 1) final 통해 상수로 사용 가능 2) 다형성 구현 가능 3) 순환 참조 방지
    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService){
        this.memberService = memberService;
    }
////    의존성 주입 방법 2. 필드 주입 방식(Autowired만 사용)
//    @Autowired
//    private MemberService memberService;

//    의존성 주입 방법 3. 어노테이션(RequiredArgs)을 이용하는 방식(1,2번의 방식의 장점) but, 다형성은 한계가 있음.
//    @RequiredArgsConstrutor: class 위에 붙여야됨.
//    @NonNull 어노테이션, final 키워드가 붙어있는 필드를 대상으로 생성자 생성
//    private final MemberService memberService;
////    아래 방식도 사용 가능
//    @NonNull
//    private MemberService memberService;

//    회원 목록 조회
//    화면 하나 만들어서 memberList 메서드에서 해당 화면 리턴
    @GetMapping("/member/list")
    public String memberList(Model model){
        List<MemberResDto> memberList = memberService.memberList();
        model.addAttribute("memberList", memberList);
        return "member/member-list";
    }

//    회원 상세 조회 : memberDetail
//    url(uri) : member/1  member/2
    @GetMapping("/member/detail/{id}")
//    int or Long 으로 받을 경우 스프링에서 형변환(String->Long)
    public String memberDetail(@PathVariable Long id, Model model){
        MemberDetailResDto memberDetResDto = memberService.memberDetail(id);
        model.addAttribute("member", memberDetResDto);

//        model.addAttribute("member", memberService.memberDetail(id));
        return "member/member-detail";
    }
//    회원 가입 화면 주고
    @GetMapping("/member/create")
    public String memberCreate(){
        return "member/member-create";
    }
//    회원 가입 데이터를 받는다

//    name, email, password
    @PostMapping("/member/create")
    public String createMember(MemberReqDto dto, Model model){
        try {
            memberService.memberCreate(dto);
//        화면 리턴이 아닌 url 재호출
            return "redirect:/member/list";
        } catch (IllegalArgumentException e){
//            System.out.println(e.getMessage());
            model.addAttribute("message", e.getMessage());
            return "member/member-error";
        }
    }

    @GetMapping("/")
    public String home(){
        return "member/home";
    }
}
