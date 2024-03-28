package com.ger.creswave.service;

import com.ger.creswave.dto.CommentDto;
import com.ger.creswave.entity.Comment;
import com.ger.creswave.entity.Post;
import com.ger.creswave.repository.CommentRepository;
import com.ger.creswave.repository.PostRepository;
import com.ger.creswave.service.impl.CommentServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class CommentServiceImplTest {

    @Mock
    private CommentRepository commentRepository;

    @Mock
    private PostRepository postRepository;

    @InjectMocks
    private CommentServiceImpl commentService;

    @Test
    void testCreateComment() {
        Post post = new Post();
        post.setId(1L);

        Comment comment = new Comment();
        comment.setId(1L);

        when(postRepository.findById(1L)).thenReturn(java.util.Optional.of(post));
        when(commentRepository.save(comment)).thenReturn(comment);

        CommentDto result = commentService.createComment(1L, new CommentDto());

        assertEquals(1L, result.getId());
    }

    @Test
    void testGetCommentsByPostId() {
        when(commentRepository.findByPostId(1L)).thenReturn(Collections.emptyList());

        List<CommentDto> result = commentService.getCommentsByPostId(1L);

        assertEquals(0, result.size());
    }

    @Test
    void testGetCommentById() {
        Post post = new Post();
        post.setId(1L);

        Comment comment = new Comment();
        comment.setPost(post);
        comment.setId(1L);

        when(postRepository.findById(1L)).thenReturn(java.util.Optional.of(post));
        when(commentRepository.findById(1L)).thenReturn(java.util.Optional.of(comment));

        CommentDto result = commentService.getCommentById(1L, 1L);

        assertEquals(1L, result.getId());
    }

    @Test
    void testUpdateComment() {
        Post post = new Post();
        post.setId(1L);

        Comment comment = new Comment();
        comment.setPost(post);
        comment.setId(1L);

        when(postRepository.findById(1L)).thenReturn(java.util.Optional.of(post));
        when(commentRepository.findById(1L)).thenReturn(java.util.Optional.of(comment));
        when(commentRepository.save(comment)).thenReturn(comment);

        CommentDto result = commentService.updateComment(1L, 1L, new CommentDto());

        assertEquals(1L, result.getId());
    }

    @Test
    void testDeleteComment() {
        Post post = new Post();
        post.setId(1L);

        Comment comment = new Comment();
        comment.setPost(post);
        comment.setId(1L);

        when(postRepository.findById(1L)).thenReturn(java.util.Optional.of(post));
        when(commentRepository.findById(1L)).thenReturn(java.util.Optional.of(comment));

        commentService.deleteComment(1L, 1L);

        //verify delete was called
        verify(commentRepository).delete(comment);
    }

}

