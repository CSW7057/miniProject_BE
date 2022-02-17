package com.sparta.miniproject0212.dto;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class urlDto {

    private String url;

    public urlDto(String url) {
        this.url = url;

    }
}
