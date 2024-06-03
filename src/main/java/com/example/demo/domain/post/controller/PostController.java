package com.example.demo.domain.post.controller;

import com.example.demo.domain.post.response.PostReq;
import com.example.demo.domain.post.response.PostRes;
import com.example.demo.domain.post.service.PostService;
import com.example.demo.global.security.userdetails.CustomUserDetails;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/post")
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class PostController {
    private final PostService postService;

    @PostMapping("")
    @Operation(summary = "게시글 작성", description = "게시글을 작성합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "게시물 작성 성공",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PostRes.class)))
    })
    public ResponseEntity<?> createPost(@AuthenticationPrincipal CustomUserDetails userDetails, @RequestBody PostReq postReq) {

        return ResponseEntity.ok(postService.createPost(postReq, userDetails));
    }


    @GetMapping("")
    @Operation(summary = "게시글 목록 조회", description = "게시글 목록을 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "게시물 전체 조회 성공",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PostRes.class)))
    })
    public ResponseEntity<?> getPostList() {

        return ResponseEntity.ok(postService.getPostList());
    }

    @GetMapping("{id}")
    @Operation(summary = "게시글 조회", description = "게시글 id를 기반으로 게시글을 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "게시물 전체 조회 성공",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PostRes.class)))
    })
    public ResponseEntity<?> getPostDetail(@PathVariable Long id) {

        return ResponseEntity.ok(postService.getPost(id));
    }

}
