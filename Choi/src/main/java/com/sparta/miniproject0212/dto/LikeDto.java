package com.sparta.miniproject0212.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LikeDto {
    private Long userId;
    private Boolean is_Check;
    private Long likeCount;


    public LikeDto(Boolean is_Check, Long likeCount ){
        this.is_Check = is_Check;
        this.likeCount =likeCount;
    }
}
