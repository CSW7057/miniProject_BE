package com.sparta.miniproject0212.service;


import com.sparta.miniproject0212.dto.DetailPostResponseDto;
import com.sparta.miniproject0212.dto.LikeDto;
import com.sparta.miniproject0212.dto.PostRequestDto;
import com.sparta.miniproject0212.dto.PostResponseDto;
import com.sparta.miniproject0212.model.Comments;
import com.sparta.miniproject0212.model.Likes;
import com.sparta.miniproject0212.model.Post;
import com.sparta.miniproject0212.repository.CommentRepository;
import com.sparta.miniproject0212.repository.LikeRepository;
import com.sparta.miniproject0212.repository.PostRepository;
import com.sparta.miniproject0212.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final LikeRepository likeRepository;
    private final UserRepository userRepository;

    @Transactional
    public Long update(Long userId, PostRequestDto postRequestDto) {
        Post post = postRepository.findById(userId).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
        post.update(postRequestDto);
        return post.getUserId();
    }

    ///////////////////////////////////////////////////////////////////////////////////////

    //포스트 작성 시 이미지 업로드
    @Transactional
    public Long Imageupdate(PostResponseDto postRequestDto) {
        Long postId = postRequestDto.getPostId();
        Post post = postRepository.findByPostId(postId).orElseThrow(
                () -> new IllegalArgumentException("포스트가 존재하지 않습니다.")
        );
        post.imageUpdate(postRequestDto);
        return post.getUserId();
    }

    // 댓글 생성 시 마다 댓글 카운트 업데이트
    @Transactional
    public Long commentCount(Long postId) {
        Post post = postRepository.findByPostId(postId).orElseThrow(
                () -> new IllegalArgumentException("포스트가 존재하지 않습니다.")
        );

        List<Comments>commentsList = commentRepository.findAllByPostId(postId);

        Long commentCount = Long.valueOf(commentsList.size());

        System.out.println("댓글 카운트 댓글 수 commentCount : " + commentCount);

        post.commentCount(commentCount);

        System.out.println("댓글 카운트 수 진행완료");

        return commentCount;
    }

    // 댓글 삭제 시 마다 댓글 카운트 업데이트
    @Transactional
    public Long deleteCommentCount(Long postId) {
        Post post = postRepository.findByPostId(postId).orElseThrow(
                () -> new IllegalArgumentException("포스트가 존재하지 않습니다.")
        );

        Long commentCount = post.getCommentCount();

        commentCount = commentCount - 1 ;

        System.out.println("댓글 카운트 댓글 수 commentCount : " + commentCount);

        post.commentCount(commentCount);

        System.out.println("댓글 카운트 수 진행완료");

        return commentCount;
    }

    //게시글 상세
    public DetailPostResponseDto detailPost(PostResponseDto postResponseDto) {

        Long postId = postResponseDto.getPostId();

        Post post = postRepository.findByPostId(postId).orElseThrow(
                () -> new IllegalArgumentException("포스트가 존재하지 않습니다.")
        );
        Long postUserId = post.getUserId();
        String nickname = userRepository.findById(postUserId).get().getNickname();


        Long userId = postResponseDto.getUserId();
        System.out.println("userId 0 체크 전");

        // is_Check
        if (userId==0) {
            System.out.println("게시글 상세 userId가 0일 때 : " +userId);
            boolean is_check = false;

            Long likeCount = post.getLikeCount();

            LikeDto likes = new LikeDto(is_check,likeCount);

            DetailPostResponseDto detailPostResponseDto = new DetailPostResponseDto(post,likes,nickname);
            return detailPostResponseDto;

        } else {
            System.out.println("Optional<Likes> like 생성 전");

            if(likeRepository.findByUserIdAndPostId(userId,postId).isPresent()){
                System.out.println("Optional<Likes> like가 있음");
                Optional<Likes> like = likeRepository.findByUserIdAndPostId(userId,postId);

                boolean is_check = like.get().getIs_Check();

                Long likeCount = post.getLikeCount();

                System.out.println("게시글 상세조회 userId 0이 아닐 때 likeCount : " + likeCount );
                LikeDto likes = new LikeDto(is_check,likeCount);
                System.out.println("LikeDto 생성완료");
                DetailPostResponseDto detailPostResponseDto = new DetailPostResponseDto(post,likes, nickname);
                System.out.println("DetailPostResponseDto 생성완료");
                return detailPostResponseDto;

            } else{

                boolean is_check = false;
                System.out.println("게시글 상세조회 userId 0이 아닐 때 is_check : " + is_check );

                Long likeCount = post.getLikeCount();
                System.out.println("게시글 상세조회 userId 0이 아닐 때 likeCount : " + likeCount );
                LikeDto likes = new LikeDto(is_check,likeCount);
                System.out.println("LikeDto 생성완료");
                DetailPostResponseDto detailPostResponseDto = new DetailPostResponseDto(post,likes, nickname);
                System.out.println("DetailPostResponseDto 생성완료");
                return detailPostResponseDto;
            }


        }

    }

    //내가 작성한 게시글 조회 서비스
    public List<DetailPostResponseDto> mypageResponse(Long userId) {

        List<Post> postList = postRepository.findAllByUserId(userId);

        //빈 리스트 선언
        List<DetailPostResponseDto> detailPostResponseDtoList = new ArrayList<>();

        //반복문 돌아서 DetailPostResponseDto 생성후 위에 리스트에 추가
        for (Post post : postList) {

            Long likeCount = post.getLikeCount();
            System.out.println("내가 작성한 게시글 조회 서비스에 like count : "+likeCount);

            DetailPostResponseDto detailPostResponseDto = new DetailPostResponseDto(post,likeCount);

            detailPostResponseDtoList.add(detailPostResponseDto);

        }
        return detailPostResponseDtoList;
    }

    //내가 관심을 누른 게시글 조회 서비스
    public List<DetailPostResponseDto> mylikeResponse(Long userId) {

        List<Likes> likesList = likeRepository.findAllByUserId(userId);

        //빈 리스트 선언
        List<DetailPostResponseDto> detailPostResponseDtoList = new ArrayList<>();

        for (Likes like : likesList) {
            Long postId = like.getPostId();

            Post post = postRepository.findByPostId(postId).orElseThrow(
                    () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
            );

            Long likeCount = post.getLikeCount();
            System.out.println("내가 작성한 게시글 조회 서비스에 like count : " + likeCount);

            DetailPostResponseDto detailPostResponseDto = new DetailPostResponseDto(post,likeCount);

            detailPostResponseDtoList.add(detailPostResponseDto);

        }

        return detailPostResponseDtoList;
    }

    // 공감 수 카운트 업데이트
    public Long likeCount(Long postId, Long userId) {

        Post post = postRepository.findByPostId(postId).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );

        //likeCount 세는 조건문
        List<Likes>likesList = likeRepository.findAllByPostId(postId);
        Long likeCount = 0L;
        for (Likes likes : likesList) {
            boolean is_check = likes.getIs_Check();
            if(is_check) {
                likeCount +=1;
            }
        }

        System.out.println("포스트에 likeCount 업데이트 수 (진행 전) : " + likeCount);
        post.likeCount(likeCount);
        postRepository.save(post);
        System.out.println("포스트에 likeCount 업데이트 수 : " + likeCount);

        return likeCount;

    }







}
