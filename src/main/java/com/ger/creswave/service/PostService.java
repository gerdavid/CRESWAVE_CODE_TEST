package com.ger.creswave.service;



import com.ger.creswave.dto.PostDto;
import com.ger.creswave.response.PostResponse;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto);

    PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir);

    PostDto getPostByTitle(String title);

    PostDto updatePost(PostDto postDto, String title);

    void deletePostByTitle(String title);

//    List<PostDto> getPostsByCategory(Long categoryId);
}
