package com.beyond.basic.controller;

import com.beyond.basic.domain.PostResDto;
import com.beyond.basic.repository.PostRepository;
import com.beyond.basic.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@ResponseBody
public class PostController {
    private final PostService postService;
    private final PostRepository postRepository;

    @Autowired
    public PostController(PostService postService, PostRepository postRepository){
        this.postService = postService;
        this.postRepository = postRepository;
    }
    @GetMapping("/post/list")
    @ResponseBody
    public List<PostResDto> postListView(){
        return postService.postList();
    }

    @GetMapping("/post/member/all")
    public void memberMemberAll(){
        System.out.println(postRepository.findAll());
    }
}
