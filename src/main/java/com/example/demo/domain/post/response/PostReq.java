package com.example.demo.domain.post.response;

import com.example.demo.domain.post.domain.Post;
import io.swagger.v3.oas.annotations.media.Schema;

public record PostReq(
        @Schema(description = "게시글 제목", example = "제목입니다.")
        String title,
        @Schema(description = "게시글 내용", example = "내용입니다.")
        String content
) {

    public Post toEntity(Post post) {
        return Post.builder()
                .title(post.getTitle())
                .content(post.getContent())
                .user(post.getUser())
                .build();
    }
}
