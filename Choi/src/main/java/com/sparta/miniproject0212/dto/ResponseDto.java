package com.sparta.miniproject0212.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseDto {
    private Long commentId;
    private String nickname;
    private String comment;
    private String insert_dt;
}
