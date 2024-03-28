package com.ger.creswave.repository;

import com.ger.creswave.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//@Repository
public interface PostRepository extends JpaRepository<Post, Long> {


    Optional<Object> findByTitle(String title);
//    List<Post> findByTitle(String title);
}
