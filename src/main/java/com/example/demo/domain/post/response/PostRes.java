package com.example.demo.domain.post.response;

import com.example.demo.domain.post.domain.Post;
import com.example.demo.domain.user.domain.User;
import io.swagger.v3.oas.annotations.media.Schema;

public record PostRes(
        @Schema(description = "게시글 ID", example = "1")
        Long id,
        @Schema(description = "게시글 작성자", example = "user")
        User user,
        @Schema(description = "게시글 제목", example = "제목입니다.")
        String title,
        @Schema(description = "게시글 내용", example = "내용입니다.")
        String content
) {

        public static PostRes from(Post post) {
            return new PostRes(post.getId(), post.getUser(), post.getTitle(), post.getContent());
        }
}
