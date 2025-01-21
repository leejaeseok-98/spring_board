package com.example.board.author.controller;

import com.example.board.author.dto.AuthorDetailRes;
import com.example.board.author.dto.AuthorListRes;
import com.example.board.author.dto.AuthorSaveReq;
import com.example.board.author.dto.AuthorUpdateReq;
import com.example.board.author.service.AuthorService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/author")
public class AuthorController {
    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }
    @PostMapping("/create")
    public String authorCreate(@Valid AuthorSaveReq dto){
        authorService.save(dto);
        return "ok";
    }

    @GetMapping("/list")
    public List<AuthorListRes> authorListRes(){
        return authorService.findAll();
    }
//    @DeleteMapping("/delete/{id}")
//    public String authorDelete(@PathVariable Long id){
//         authorService.delete(id);
//         return "ok";
//    }

    @GetMapping("/delete/{id}")
    public String authorDelete(@PathVariable Long id){
        authorService.delete(id);
        return "ok";
    }

    @GetMapping("/detail/{id}")
    public AuthorDetailRes authorDetail(@PathVariable Long id){
        return authorService.findById(id);
    }

    @PostMapping("/update/{id}")
    public String authorUpdate(@PathVariable Long id, AuthorUpdateReq dto){
        authorService.update(id,dto);
        return "ok";
    }
}
