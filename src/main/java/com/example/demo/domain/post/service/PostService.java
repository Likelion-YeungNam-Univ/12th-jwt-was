package com.example.demo.domain.post.service;

import com.example.demo.domain.post.domain.Post;
import com.example.demo.domain.post.repository.PostRepository;
import com.example.demo.domain.post.response.PostReq;
import com.example.demo.domain.post.response.PostRes;
import com.example.demo.domain.user.domain.User;
import com.example.demo.domain.user.repository.UserRepository;
import com.example.demo.global.security.userdetails.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Transactional
    public PostRes createPost(PostReq postReq, CustomUserDetails userDetails) {

        User user = userRepository.findById(userDetails.getUserId()).get();
        log.info("게시글 제목 {}, 게시글 내용 {}", postReq.title(), postReq.content());

        Post post = Post.builder()
                .title(postReq.title())
                .content(postReq.content())
                .user(user)
                .build();

        return PostRes.from(postRepository.save(post));
    }

    @Transactional(readOnly = true)
    public PostRes getPost(Long id) {
        Post post = postRepository.findById(id).get();

        return PostRes.from(post);
    }

    @Transactional(readOnly = true)
    public List<PostRes> getPostList() {
        List<Post> postList = postRepository.findAll();
        List<PostRes> postResList = new ArrayList<>();

        for (Post post : postList) {
            postResList.add(PostRes.from(post));
        }

        return postResList;
    }
}
