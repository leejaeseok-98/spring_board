package com.example.board.author;

import com.example.board.author.domain.Author;
import com.example.board.author.domain.Role;
import com.example.board.author.dto.AuthorSaveReq;
import com.example.board.author.repository.AuthorRepository;
import com.example.board.author.service.AuthorService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class AuthorServiceTest {
    @Autowired
    private AuthorService authorService;
    @Autowired
    private AuthorRepository authorRepository;
    @Test
    public void authorSaveTest(){
        AuthorSaveReq authorSaveReq = new AuthorSaveReq(
                "hongildong","wotjrdl98@naver.com","1234"
                , Role.ADMIN
        );
        authorService.save(authorSaveReq);
        Author author = authorRepository.findByEmail("wotjrdl98@naver.com").orElse(null);
        Assertions.assertEquals(authorSaveReq.getEmail(),author.getEmail());

    }

    @Test
    public void findAllTest(){
        int beforeSize = authorService.findAll().size();
        authorRepository.save(Author.builder().email("aaaa@naver.com").name("aaa").password("12345").build());
        authorRepository.save(Author.builder().email("bbbb@naver.com").name("bbb").password("12345").build());
        authorRepository.save(Author.builder().email("cccc@naver.com").name("ccc").password("12345").build());
        int afterSize = authorService.findAll().size();
        Assertions.assertEquals(beforeSize+3,afterSize);
    }
}
