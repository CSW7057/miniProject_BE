package com.sparta.miniproject0212.service;


import com.sparta.miniproject0212.dto.CommentRequestDto;
import com.sparta.miniproject0212.dto.ResponseDto;
import com.sparta.miniproject0212.model.Comments;
import com.sparta.miniproject0212.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostService postService;

    @Autowired
    public CommentService(CommentRepository commentRepository, PostService postService){
        this.commentRepository = commentRepository;
        this.postService =postService;
    }

    //댓글생성
    public Comments createComment(CommentRequestDto commentRequestDto){

        Comments comments = new Comments(commentRequestDto);
        commentRepository.save(comments);

        //포스트에 댓글 카운트 업데이트 실행
        Long postId = commentRequestDto.getPostId();
        postService.commentCount(postId);

        return comments;
    }

    //댓글 조회
    public List<ResponseDto> getComments(Long postId){
        List<Comments> comments = commentRepository.findAllByPostId(postId);
        List<ResponseDto> responseDtoList = new ArrayList<>();
        for (int i=0; i<comments.size(); i++){
            ResponseDto responseDto = new ResponseDto();
            responseDto.setCommentId(comments.get(i).getId());
            responseDto.setNickname(comments.get(i).getNickname());
            responseDto.setComment(comments.get(i).getComment());
            responseDto.setInsert_dt(comments.get(i).getInsert_dt());
            responseDtoList.add(responseDto);
        }
        return responseDtoList;
    }

    //댓글 수정
    @Transactional
    public Comments updateComment(Long commentId, CommentRequestDto commentRequestDto){
        Comments comments =commentRepository.findById(commentId).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
        comments.update(commentRequestDto);
        return comments;
    }

    //댓글 삭제
    public void deleteComment(Long commentId) {

        System.out.println("delete commentId : " + commentId);

        commentRepository.deleteById(commentId);

    }
}