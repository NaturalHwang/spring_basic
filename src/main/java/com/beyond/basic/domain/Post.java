package com.beyond.basic.domain;

import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
public class Post extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(nullable = false)
    String title;
    @JoinColumn(name = "member_id"/*, unique = true*/)
//    1:1의 경우 OneToOne 설정하고, unique = true 설정
//    ManyToOne, OneToOne의 경우에 default 설정이 Eager이므로, lazy로 변경
    @ManyToOne(fetch = FetchType.LAZY) // 반대쪽에  OneToMany 반드시 등록할 필요 없음. FK는 웬만하면 붙이는 것 같음.
//    JPA의 영속성(persistence) 컨텍스트에 의해 Member 객체가 관리
    private Member member;

    public PostResDto listFromEntity(){
        return new PostResDto(this.id, this.title);
    }
}
