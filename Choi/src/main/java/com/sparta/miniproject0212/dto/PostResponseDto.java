package com.sparta.miniproject0212.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;



@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PostResponseDto {

    private Long postId;
    private Long userId;
    private String title;
    private String content;
    private String imageUrl;
    private Long commentCount;

}
