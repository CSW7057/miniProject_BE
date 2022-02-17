package com.sparta.miniproject0212.controller;


import com.sparta.miniproject0212.dto.DetailPostResponseDto;
import com.sparta.miniproject0212.dto.PostRequestDto;
import com.sparta.miniproject0212.dto.PostResponseDto;
import com.sparta.miniproject0212.model.Comments;
import com.sparta.miniproject0212.model.Likes;
import com.sparta.miniproject0212.model.Post;
import com.sparta.miniproject0212.repository.CommentRepository;
import com.sparta.miniproject0212.repository.LikeRepository;
import com.sparta.miniproject0212.repository.PostRepository;
import com.sparta.miniproject0212.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class PostConroller {

    private final PostRepository postRepository;
    private final PostService postService;
    private final CommentRepository commentRepository;
    private final LikeRepository likeRepository;

    // 게시글 전체 조회
    @GetMapping("/user/main")
    public List<Post> Posts() {

        return postRepository.findAllByOrderByCreatedAtDesc();
    }

    // 카운트 베스트10개 조회
    @GetMapping("/user/best")
    public List<Post> bestPosts() {
        List<Post> totalList = postRepository.findAllByOrderByLikeCountDesc();
        List<Post> postList = new ArrayList<>();
        for (int i =0; i<10; i++) {
            postList.add(totalList.get(i));
        }
        return postList;
    }



//    // 게시글 상세 조회
//    @GetMapping("/user/post/{postId}")
//    public Post detailPost(@PathVariable Long postId) {
//        Post post = new Post();
//        post = postRepository.findById(postId).orElseThrow(
//                () -> new IllegalArgumentException("게시글이 없습니다.")
//        );
//        return post;
//    }

    /////////////////////////////////////////////////////////////////

    // 내가 작성한 게시글 조회
    @PostMapping("/api/post/mypage")
    public List<DetailPostResponseDto> myWritePost(@RequestBody PostRequestDto postRequestDto) {

        Long userId = postRequestDto.getUserId();

        List<DetailPostResponseDto> detailPostResponseDto = postService.mypageResponse(userId);

        return detailPostResponseDto;
    }

    // 내가 관심을 누른 게시글 조회
    @PostMapping("/api/post/mylikepage")
    public List<DetailPostResponseDto> myLikePost(@RequestBody PostRequestDto postRequestDto) {

        Long userId = postRequestDto.getUserId();

        List<DetailPostResponseDto> detailPostResponseDto = postService.mylikeResponse(userId);

        return detailPostResponseDto;
    }

    // 댓글 삭제 시 카운트 api
    @PostMapping("/api/count/{commentId}")
    public Long commentCount(@PathVariable Long commentId) {

        System.out.println("댓글 카운트 api commentId :" + commentId);

        //포스트에 댓글 카운트 업데이트 실행
        Long postId = commentRepository.findById(commentId).get().getPostId();
        System.out.println("postId : " + postId );
        postService.deleteCommentCount(postId);

        //postService.commentCount(commentId);

        return commentId;
    }


    // 게시글 상세 조회
    @PostMapping("/user/post/detail")
    public DetailPostResponseDto detailPost(@RequestBody PostResponseDto postResponseDto) {

        System.out.println("게시글 상세조회 postId " + postResponseDto.getPostId());
        System.out.println("게시글 상세조회 userId " + postResponseDto.getUserId());
        DetailPostResponseDto detailPostResponseDto = postService.detailPost(postResponseDto);

        return detailPostResponseDto;
    }

    ///////////////////////////////////////////////////////////////////////////////


    // 게시글 작성
    @PostMapping("/api/post/write")
    public Post writePost(@RequestBody PostRequestDto postRequestDto) {
        System.out.println("게시글 생성 내용 : " + postRequestDto.getContent());
        System.out.println("게시글 생성 제목 : " + postRequestDto.getTitle());
        System.out.println("게시글 생성 이미지url : " + postRequestDto.getImageUrl());
        System.out.println("게시글 생성 유저Id : " + postRequestDto.getUserId());

        Post post = new Post(postRequestDto);
        postRepository.save(post);
        return post;
    }

    // 게시글 작성 시 이미지 업로드
    @PutMapping("/api/post/write/image")
    public Long imagePost(@RequestBody PostResponseDto PostResponseDto) {
        System.out.println("게시글 이미지 업로드 이미지url : " + PostResponseDto.getImageUrl());
        System.out.println("게시글 이미지 업로드 postId : " + PostResponseDto.getPostId());

        return postService.Imageupdate(PostResponseDto);

    }

    // 게시글 수정
    @PutMapping("/api/post/write/{postId}")
    public Long updatePost(@PathVariable Long postId, @RequestBody PostRequestDto postRequestDto) {
        return postService.update(postId, postRequestDto);
    }

    //게시글 삭제
    @DeleteMapping("/api/post/{postId}")
    public Long deletePost(@PathVariable Long postId) {

        postRepository.deleteById(postId);
        System.out.println("포스트 삭제 postId = " + postId );

        List<Comments> commentsList = commentRepository.findAllByPostId(postId);
        commentRepository.deleteAll(commentsList);
        System.out.println("댓글들 삭제완료 postId = " + postId );

        List<Likes>likesList = likeRepository.findAllByPostId(postId);
        likeRepository.deleteAll(likesList);
        System.out.println("공감 객체들 삭제완료 postId = " + postId );

        return postId;
    }



}
