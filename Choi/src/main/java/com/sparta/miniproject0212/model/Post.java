package com.sparta.miniproject0212.model;

import com.sparta.miniproject0212.dto.PostRequestDto;
import com.sparta.miniproject0212.dto.PostResponseDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor // 기본 생성자 만듬.
@Getter // 조회를 하기 위해 있어야 됨.
@Entity // 테이블과 연계됨을 스프링에게 알려줌
public class Post extends Timestamped { // 생성 , 수정 시간을 자동으로 만듬.
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long postId;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column
    private String imageUrl;

    @Column
    private Long commentCount;

    @Column
    private Long likeCount;

//    @OneToMany
//    @JoinColumn(name = "COMMENTS_ID")
//    private List<Comments> commentList;

    public Post(PostRequestDto postRequestDto)  {
        this.userId = postRequestDto.getUserId();
        this.title = postRequestDto.getTitle();
        this.content = postRequestDto.getContent();
        this.imageUrl = postRequestDto.getImageUrl();
        this.commentCount = 0L;
        this.likeCount = 0L;

    }
    public void update(PostRequestDto postRequestDto) {
        this.title = postRequestDto.getTitle();
        this.content = postRequestDto.getContent();
        this.imageUrl = postRequestDto.getImageUrl();
    }

    //이미지 업데이트 할때 필요
    public void imageUpdate(PostResponseDto postResponseDto) {
        this.imageUrl = postResponseDto.getImageUrl();
    }

    //comment 업데이트 할때 필요
    public void commentCount(Long Count) {
        this.commentCount = Count;
    }

    //comment 업데이트 할때 필요
    public void likeCount(Long Count) {
        this.likeCount = Count;
    }


}
