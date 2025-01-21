package com.example.board.author.service;

import com.example.board.author.domain.Author;
import com.example.board.author.dto.AuthorDetailRes;
import com.example.board.author.dto.AuthorListRes;
import com.example.board.author.dto.AuthorSaveReq;
import com.example.board.author.dto.AuthorUpdateReq;
import com.example.board.author.repository.AuthorRepository;
import com.example.board.post.domain.Post;
import com.example.board.post.repository.PostRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class AuthorService {

    private final AuthorRepository authorRepository;
    private final PostRepository restRepository;

    public AuthorService(AuthorRepository authorRepository, PostRepository restRepository) {
        this.authorRepository = authorRepository;
        this.restRepository = restRepository;
    }

    public void save(AuthorSaveReq dto){
        if (authorRepository.findByEmail(dto.getEmail()).isPresent()){
            throw new IllegalArgumentException("이미 존재하는 이메일입니다.");
        }
        Author author = authorRepository.save(dto.toEntity());
        restRepository.save(Post.builder().title("반갑습니다").author(author).build());
//        cascade를 활용해서, post데이터를 합께 만드는 경우
//        Author author = Author.builder().name(dto.getName()).email(dto.getEmail()).role(dto.getRole()).password(dto.getPassword())
//                .build();
//        post를 생성하는 시점에 author가 아직 DB에 저장되지 않은 것으로 보여지나,JPA가 AUTHOR와 POST를 SAVE하는 시점에
//        선후관계를 맞춰줌
//        author.getPosts().add(Post.builder().title("안녕하세요1 반갑습니다.").author(author).build());
//        author.getPosts().add(Post.builder().title("안녕하세요2- 반갑습니다.").author(author).build());
//        authorRepository.save(author);
    }
    public List<AuthorListRes> findAll(){
        return authorRepository.findAll().stream().map(a ->a.listDtofromEntity()).collect(Collectors.toList());
    }

    public void delete(Long id){
        Author author =authorRepository.findById(id).orElseThrow(()->new EntityNotFoundException("없다"));
        authorRepository.delete(author);
    }
    public AuthorDetailRes findById(Long id){
        Author author = authorRepository.findById(id).orElseThrow(()->new EntityNotFoundException("없다"));
        return author.toDetailDto();
    }

    public void update(Long id, AuthorUpdateReq dto){
        Author author = authorRepository.findById(id).orElseThrow(()->new EntityNotFoundException("없다"));
        author.updateProfile(dto);
//        기존객체에 변경이 발생할 경우 ,별도의 save 없어도 jpa가 엔티티의 변경을 자동인지하고, 변경사항을 DB반영
//        이를 dirtychecking이라 부르고, 반드시 transactional어노테이션이 있을 경우 동작.
    }

}
