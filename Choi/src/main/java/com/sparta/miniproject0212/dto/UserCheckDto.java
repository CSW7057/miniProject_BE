package com.sparta.miniproject0212.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserCheckDto {

    private String username;
    private String nickname;
    private Long userId;
    private boolean check;


    public UserCheckDto(String username,String nickname, Long userId, boolean check) {
        this.username = username;
        this.nickname = nickname;
        this.userId = userId;
        this.check = check;
    }

}
