package com.sparta.miniproject0212.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentRequestDto {
    private Long userId;
    private Long postId;
    private String comment;
    private String insert_dt;
    private String nickname;
}