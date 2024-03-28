package com.ger.creswave.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import com.ger.creswave.dto.PostDto;
import com.ger.creswave.entity.Post;
import com.ger.creswave.repository.PostRepository;
import com.ger.creswave.service.impl.PostServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

@ExtendWith(MockitoExtension.class)
public class PostServiceImplTest {

    @Mock
    private PostRepository postRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private PostServiceImpl postService;

    @Test
    public void testCreatePost() {
        PostDto postDto = new PostDto();
        postDto.setTitle("Test Title");

        Post post = new Post();
        post.setTitle("Test Title");

        when(modelMapper.map(postDto, Post.class)).thenReturn(post);

        when(postRepository.save(post)).thenReturn(post);

        when(modelMapper.map(post, PostDto.class)).thenReturn(postDto);

        PostDto createdDto = postService.createPost(postDto);

        assertNotNull(createdDto);
        assertEquals("Test Title", createdDto.getTitle());
    }

    @Test
    public void testGetPostByTitle() throws Exception {
        Post post = new Post();
        post.setTitle("Test Title");

        when(postRepository.findByTitle("Test Title")).thenReturn(java.util.Optional.of(post));

        when(modelMapper.map(post, PostDto.class)).thenReturn(new PostDto());

        PostDto postDto = postService.getPostByTitle("Test Title");

        assertNotNull(postDto);
        assertEquals("Test Title", postDto.getTitle());
    }

    @Test
    public void testUpdatePost() throws Exception {
        Post post = new Post();
        post.setTitle("New Title");

        when(postRepository.findByTitle("Old Title")).thenReturn(java.util.Optional.of(new Post()));
        when(postRepository.save(post)).thenReturn(post);
        when(modelMapper.map(post, PostDto.class)).thenReturn(new PostDto());

        PostDto updated = postService.updatePost(new PostDto(), "Old Title");

        assertNotNull(updated);
        assertEquals("New Title", updated.getTitle());
    }

    @Test
    public void testDeletePost() throws Exception {
        Post post = new Post();
        post.setTitle("Test Title");

        when(postRepository.findByTitle("Test Title")).thenReturn(java.util.Optional.of(post));

        postService.deletePostByTitle("Test Title");

    }

}

