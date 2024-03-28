package com.ger.creswave.controller;

// Required imports
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.ger.creswave.entity.Post;
import com.ger.creswave.exception.ResourceNotFoundException;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ger.creswave.service.PostService;
import com.ger.creswave.repository.PostRepository;
import com.ger.creswave.dto.PostDto;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class PostServiceTests {

    @Mock
    private PostRepository postRepository;

    @InjectMocks
    private PostService postService;

    @Test
    public void testCreatePost() {
        PostDto postDto = new PostDto();
        postDto.setTitle("New Post");

        Post savedPost = new Post();
        savedPost.setTitle("New Post");

        when(postRepository.save(any(Post.class))).thenReturn(savedPost);

        PostDto result = postService.createPost(postDto);

        assertEquals("New Post", result.getTitle());
        verify(postRepository, times(1)).save(any(Post.class));
    }

    @Test
    public void testCreatePostWithInvalidTitle() {
        PostDto postDto = new PostDto();

        assertThrows(ConstraintViolationException.class, () -> {
            postService.createPost(postDto);
        });
    }

    @Test
    public void testGetPostByTitle() {
        Post post = new Post();
        post.setTitle("Test Post");

        when(postRepository.findByTitle("Test Post")).thenReturn(Optional.of(post));

        PostDto result = postService.getPostByTitle("Test Post");

        assertEquals("Test Post", result.getTitle());
        verify(postRepository, times(1)).findByTitle("Test Post");
    }

    @Test
    public void testGetPostByTitleNotFound() {
        when(postRepository.findByTitle("Test Post")).thenReturn(null);

        assertThrows(ResourceNotFoundException.class, () -> {
            postService.getPostByTitle("Test Post");
        });
    }

}

