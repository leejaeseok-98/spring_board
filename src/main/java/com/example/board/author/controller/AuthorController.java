package com.example.board.author.controller;

import com.example.board.author.domain.Author;
import com.example.board.author.dto.AuthorDetailRes;
import com.example.board.author.dto.AuthorListRes;
import com.example.board.author.dto.AuthorSaveReq;
import com.example.board.author.dto.AuthorUpdateReq;
import com.example.board.author.service.AuthorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/author")
public class AuthorController {
    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/login")
    public String authorLoginScreen(){
        return "/author/author_login";
    }

    @GetMapping("/create")
    public String authorCreateScreen(){
        return "/author/author_create";
    }
    @PostMapping("/create")
    public String authorCreate(@Valid AuthorSaveReq dto){
        authorService.save(dto);
        return "redirect:/";
    }

    @GetMapping("/list")
    public  String authorListRes(Model model){
        List<AuthorListRes> authorListResList = authorService.findAll();
        model.addAttribute("authorList",authorListResList);
        return "author/author_list";
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

//    restapi
//    @GetMapping("/delete/{id}")
//    public String authorDelete(@PathVariable Long id){
//        authorService.delete(id);
//        return "ok";
//    }


    @GetMapping("/detail/{id}")
    public String authorDetail(@PathVariable Long id, Model model){
        AuthorDetailRes authorDetailRes = authorService.findById(id);
//        "author"라는 변수에 데이터 세팅해서 화면 리턴
        model.addAttribute("author", authorDetailRes);
        return "/author/author_detail";
    }

    //    restapi
//    @GetMapping("/detail/{id}")
//    public AuthorDetailRes authorDetail(@PathVariable Long id){
//        return authorService.findById(id);
//    }

    @PostMapping("/update/{id}")
    public String authorUpdate(@PathVariable Long id, AuthorUpdateReq dto){
        authorService.update(id,dto);
        return "ok";
    }
    //    restapi
//    @PostMapping("/update/{id}")
//    public String authorUpdate(@PathVariable Long id, AuthorUpdateReq dto){
//        authorService.update(id,dto);
//        return "ok";
//    }
}
