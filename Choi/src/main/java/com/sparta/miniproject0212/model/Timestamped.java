package com.sparta.miniproject0212.model;


import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class) // 생성/변경 시간을 자동 업데이트
public abstract class Timestamped {

    @CreatedDate // localdatetime 시간을 나타내주는 데이터타입
    private LocalDateTime createdAt;

    @LastModifiedDate // 작성 및 수정 시간
    private LocalDateTime modifiedAt;


}
