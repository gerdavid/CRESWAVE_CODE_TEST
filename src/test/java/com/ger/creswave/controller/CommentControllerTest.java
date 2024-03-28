package com.ger.creswave.controller;

import com.ger.creswave.dto.CommentDto;
import jakarta.validation.ValidationException;
import org.junit.jupiter.api.Test;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CommentControllerTest {

    private CommentController commentController = new CommentController(null);

    @Test
    void testCreateComment() {
        CommentDto comment = new CommentDto();
        comment.setId(1L);
        comment.setEmail("new@email");
        comment.setBody("New comment");

        // Test successful creation
        CommentDto created = commentController.createComment(1L, comment).getBody();
        assertEquals(1L, created.getId());

        // Test validation error
        Exception exception = assertThrows(ValidationException.class, () -> {
            commentController.createComment(1L, null);
        });
    }

    @Test
    void testGetCommentsByPostId() {
        // Test valid postId
        List<CommentDto> comments = commentController.getCommentsByPostId(1L);
        assertEquals(1, comments.size());

        // Test invalid postId
        comments = commentController.getCommentsByPostId(-1L);
        assertEquals(0, comments.size());
    }

    @Test
    void testGetCommentById() {
        // Test valid id
        CommentDto comment = commentController.getCommentById(1L, 1L).getBody();
        assertEquals(1L, comment.getId());

        // Test invalid id
        comment = commentController.getCommentById(1L, -1L).getBody();
        assertEquals(null, comment);
    }

    @Test
    void testUpdateComment() {
        CommentDto comment = new CommentDto();
        comment.setId(1L);
        comment.setBody("Updated");

        // Test valid update
        CommentDto updated = commentController.updateComment(1L, 1L, comment).getBody();
        assertEquals("Updated", updated.getBody());

        // Test invalid update
        Exception exception = assertThrows(ChangeSetPersister.NotFoundException.class, () -> {
            commentController.updateComment(1L, -1L, null);
        });
    }

    @Test
    void testDeleteComment() {
        // Test valid delete
        commentController.deleteComment(1L, 1L);

        // Test invalid delete
        Exception exception = assertThrows(ChangeSetPersister.NotFoundException.class, () -> {
            commentController.deleteComment(1L, -1L);
        });
    }

}


