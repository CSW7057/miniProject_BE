package com.sparta.miniproject0212.repository;



import com.sparta.miniproject0212.model.Comments;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comments, Long> {
    //Comments 테이블에서 찾아오니까 List<Comments>
    List<Comments> findAllByPostId(Long postId);
    //Long postId 가 들어간 이유는 postid에 관한 것들을 다 찾아오니까.
}
