package com.sparta.miniproject0212.repository;


import com.sparta.miniproject0212.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByOrderByModifiedAtDesc();
    List<Post> findAllByOrderByCreatedAtDesc();
    Optional<Post> findByPostId(Long postId);
    List<Post> findAllByUserId (Long userId);
    List<Post> findAllByPostId (Long postId);
    List<Post> findAllByOrderByLikeCountDesc ();
}
