package com.beyond.basic.domain;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class Student {
    private String name;
    private String email;
//    private List<Map<String, String>> grades;
//    private List<Student.Grade> grades;
    private List<Student.Grade> grades;
    @Data
    static class Grade{
        private String className;
        private String point;
    }
}
//@Data
//class Grade{
//    private String className;
//    private String point;
//}

