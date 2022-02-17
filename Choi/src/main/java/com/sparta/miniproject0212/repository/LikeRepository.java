package com.sparta.miniproject0212.repository;


import com.sparta.miniproject0212.model.Likes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LikeRepository extends JpaRepository<Likes, Long> {
    Optional<Likes> findByUserIdAndPostId(Long userId, Long postId);
    List<Likes> findAllByPostId(Long postId);
    List<Likes> findAllByUserId(Long userId);
    Optional<Likes> findByPostId(Long postId);

}
