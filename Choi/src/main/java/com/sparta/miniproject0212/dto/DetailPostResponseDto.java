package com.sparta.miniproject0212.dto;


import com.sparta.miniproject0212.model.Post;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;


@Getter
public class DetailPostResponseDto {

    private Long postId;
    private String title;
    private String content;
    private String imageUrl;
    private Long commentCount;
    private LikeDto likeDto;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private Long userId;
    private Long likeCount;
    private String nickname;

    public DetailPostResponseDto(Post post, LikeDto likeDto, String nickname ) {
        this.postId = post.getPostId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.imageUrl =post.getImageUrl();
        this.commentCount =post.getCommentCount();
        this.createdAt = post.getCreatedAt();
        this.modifiedAt =post.getModifiedAt();
        this.userId = post.getUserId();
        this.likeDto =likeDto;
        this.nickname = nickname;

    }

    public DetailPostResponseDto(Post post, Long likeCount) {
        this.postId = post.getPostId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.imageUrl =post.getImageUrl();
        this.commentCount =post.getCommentCount();
        this.createdAt = post.getCreatedAt();
        this.likeCount = likeCount;
    }





}
