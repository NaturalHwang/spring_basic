package com.beyond.basic.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

//@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class MemberDetailResDto {
    private Long id;
    private String name;
    private String email;
    private String password;
    private String createdTime;

    public MemberDetailResDto(String name, String email, String password, String createdTime){
        this.name = name;
        this.email = email;
        this.password = password;
        this.createdTime = createdTime;
    }
}
