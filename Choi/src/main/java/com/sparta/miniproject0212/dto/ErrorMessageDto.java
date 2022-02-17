package com.sparta.miniproject0212.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ErrorMessageDto {

    private String statusHttp;
    private String errorMessage;
    private String url;

    public ErrorMessageDto(String statusHttp, String errorMessage,String url ) {
        this.statusHttp = statusHttp;
        this.errorMessage = errorMessage;
        this.url = url;
    }
}

