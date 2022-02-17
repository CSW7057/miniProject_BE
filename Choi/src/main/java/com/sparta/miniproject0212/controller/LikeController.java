package com.sparta.miniproject0212.controller;


import com.sparta.miniproject0212.dto.LikeDto;
import com.sparta.miniproject0212.model.Likes;
import com.sparta.miniproject0212.repository.LikeRepository;
import com.sparta.miniproject0212.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class LikeController {

    private final LikeRepository likeRepository;
    private final LikeService likeService;

    @Autowired
    public LikeController(LikeRepository likeRepository, LikeService likeService) {
        this.likeRepository = likeRepository;
        this.likeService = likeService;
    }

    //클라이언트가 관심 눌럿을 때
    @PutMapping("/api/posts/{postId}")
    public Long updatelike(@PathVariable Long postId, @RequestBody LikeDto likeDto) {
        //객체가 있으면 수정
        if (likeRepository.findByUserIdAndPostId(likeDto.getUserId(), postId).isPresent()) {
            Long likeCount = likeService.updatelike(postId, likeDto);
            return likeCount;
        }
        //객체가 없으면 생성
        else {
            Long likeCount = likeService.createlike(postId, likeDto);
            return likeCount;
        }
    }


}
