package com.sparta.miniproject0212.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor // 필드값을 모두 포함한 생성자를 자동 생성.
@NoArgsConstructor // 기본생성자 만듬
public class PostRequestDto {

    private Long userId;
    private String title;
    private String content;
    private String imageUrl;

}
