package com.beyond.basic.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

//@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class MemberResDto {
    private Long id;
    private String name;
    private String email;
}

