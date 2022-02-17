package com.sparta.miniproject0212.model;


import com.sparta.miniproject0212.dto.CommentRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
public class Comments  {

    //게시글 아이디가 들어갈 때는 userId , 댓글이 들어가기 위해선 PostId입니다.
    //댓글 삭제 시 Long id,== commentId이니까.

    //댓글 아이디 = commentId
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    //댓글 누가 작성했는지 알아야 한다.
    //댓글 삭제하기. 수정 하기 위해서.
    @Column(nullable = false)
    private Long userId;

    //게시글

    @Column(nullable = false)
    private Long postId;

    //닉네임
    @Column(nullable = false)
    private String nickname;

    //댓글내용
    @Column(nullable = false)
    private String comment;

    //생성시간
    @Column(nullable = false)
    private String insert_dt;

    public Comments(CommentRequestDto commentRequestDto){
        this.userId = commentRequestDto.getUserId();
        this.postId = commentRequestDto.getPostId();
        this.comment = commentRequestDto.getComment();
        this.insert_dt = commentRequestDto.getInsert_dt();
        this.nickname = commentRequestDto.getNickname();
    }

    public void update(CommentRequestDto commentRequestDto){
        this.comment = commentRequestDto.getComment();
    }

}