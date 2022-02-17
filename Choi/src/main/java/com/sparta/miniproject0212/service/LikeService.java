package com.sparta.miniproject0212.service;


import com.sparta.miniproject0212.dto.LikeDto;
import com.sparta.miniproject0212.model.Likes;
import com.sparta.miniproject0212.repository.LikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Service
public class LikeService {

    private final LikeRepository likeRepository;
    private final PostService postService;

    @Autowired
    public LikeService(LikeRepository likeRepository, PostService postService){

        this.likeRepository=likeRepository;
        this.postService=postService;
    }


    // 관심 객체 없을 때 (처음 눌렀을 때)
    public Long createlike(Long postId, LikeDto likeDto){
        Likes like = new Likes(postId, likeDto);
        likeRepository.save(like); // repository에는 Like 객체 자체가 들어가야만 된다.
        System.out.println(postId +"관심 눌러서 객체 생성");

        //공감 카운트 수 업데이트 기능
        Long userId = likeDto.getUserId();
        Long likeCount = postService.likeCount(postId,userId);

        return likeCount;
    }

    // 관심 객체 생성 후 클릭 시
    public Long updatelike(Long postId, LikeDto likeDto) {
        Likes like = likeRepository.findByUserIdAndPostId(likeDto.getUserId(), postId).orElseThrow(
                ()-> new NullPointerException("아이디가 존재하지 않습니다.")
        );
        like.update(likeDto);
        likeRepository.save(like);

        //공감 카운트 수 업데이트 기능
        Long userId = likeDto.getUserId();
        Long likeCount = postService.likeCount(postId,userId);

        return likeCount;
    }
}
