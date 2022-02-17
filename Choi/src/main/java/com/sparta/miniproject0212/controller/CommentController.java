package com.sparta.miniproject0212.controller;


import com.sparta.miniproject0212.dto.CommentRequestDto;
import com.sparta.miniproject0212.dto.ResponseDto;
import com.sparta.miniproject0212.model.Comments;
import com.sparta.miniproject0212.repository.CommentRepository;
import com.sparta.miniproject0212.service.CommentService;
import com.sparta.miniproject0212.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentController {

    private final CommentService commentService;
    private final CommentRepository commentRepository;
    private final PostService postService;

    @Autowired
    public CommentController(CommentService commentService, CommentRepository commentRepository, PostService postService) {
        this.commentService = commentService;
        this.commentRepository = commentRepository;
        this.postService = postService;
    }

    //댓글 생성
    @PostMapping("/api/comments/write")
    public Comments createComment(@RequestBody CommentRequestDto commentRequestDto) {

        System.out.println("댓글 생성 내용 : "+commentRequestDto.getComment());
        System.out.println("댓글 생성  유저아디 : "+commentRequestDto.getUserId());
        System.out.println("댓글 생성  포스트아디 : "+commentRequestDto.getPostId());
        System.out.println( "댓글 생성  날짜 : " + commentRequestDto.getInsert_dt());
        System.out.println("댓글 생성  닉네임 :" + commentRequestDto.getNickname());

        Comments comments = commentService.createComment(commentRequestDto);
        return comments;
    }

    //댓글 조회 : postId 상세 페이지에 있는 commentId를 가져와야 된다.
    @GetMapping("/user/comments/{postId}")
    public List<ResponseDto> getComments(@PathVariable Long postId) {
        List<ResponseDto> comments = commentService.getComments(postId);
        return comments;
    }


    //댓글 삭제
    @DeleteMapping("/api/comments/{commentId}")
    //@PathVariable : 주소에 있는 아이디를 변수로 받기 위해, 경로에 있는 변수.
    public Long deleteComment(@PathVariable Long commentId) {

        commentService.deleteComment(commentId);

//        //포스트에 댓글 카운트 업데이트 실행
//        Long postId = commentRepository.findById(commentId).get().getPostId();
//        System.out.println("postId : " + postId );
//        postService.commentCount(postId);

        return commentId;
    }

    //댓글 수정
    @PutMapping("/api/comments/{commentId}")
    public Long updateComment(@PathVariable Long commentId, @RequestBody CommentRequestDto commentRequestDto){
        Comments comments = commentService.updateComment(commentId, commentRequestDto);
        return comments.getId();
    }

}