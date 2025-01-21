package com.example.board.post.controller;

import com.example.board.post.dto.PostDetailRes;
import com.example.board.post.dto.PostListRes;
import com.example.board.post.dto.PostSaveReq;
import com.example.board.post.dto.PostUpdateReq;
import com.example.board.post.service.PostService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/create")
    public String postCreate(@Valid PostSaveReq dto){
        postService.save(dto);
        return "ok";
    }
    @GetMapping("/list")
    public List<PostListRes> postList(){
        return postService.findAll();
    }
    @GetMapping("/detail/{id}")
    public PostDetailRes detailRes(@PathVariable Long id){
        PostDetailRes postDetailRes = postService.findById(id);
        return postDetailRes;
    }

    @PostMapping("/update/{id}")
    public String updateReq(@PathVariable Long id, PostUpdateReq postUpdateReq){
        postService.update(id,postUpdateReq);
        return "ok";
    }
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id){
        postService.delete(id);
        return "ok";
    }
}
