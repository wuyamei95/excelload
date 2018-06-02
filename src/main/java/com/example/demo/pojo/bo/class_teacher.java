package com.example.demo.pojo.bo;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class class_teacher {
    @NotNull
    int teacher_id;
    @NotNull
    String class_name;
}
