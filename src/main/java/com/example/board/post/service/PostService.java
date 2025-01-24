package com.example.board.post.service;

import com.example.board.author.domain.Author;
import com.example.board.author.repository.AuthorRepository;
import com.example.board.post.domain.Post;
import com.example.board.post.dto.PostDetailRes;
import com.example.board.post.dto.PostListRes;
import com.example.board.post.dto.PostSaveReq;
import com.example.board.post.dto.PostUpdateReq;
import com.example.board.post.repository.PostRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PostService {
    private final PostRepository postRepository;
    private final AuthorRepository authorRepository;

    public PostService(PostRepository postRepository, AuthorRepository authorRepository) {
        this.postRepository = postRepository;
        this.authorRepository = authorRepository;
    }

    public void save(PostSaveReq postSaveReq) {
        Author author = authorRepository.findByEmail(postSaveReq.getEmail()).orElseThrow(() -> new EntityNotFoundException());
        LocalDateTime appointTime = null;
        DateTimeFormatter dateTimeFormatter = null;
        if (postSaveReq.getAppoint().equals("Y")) {
            if (postSaveReq.getAppointmentTime() == null || postSaveReq.getAppointmentTime().isEmpty()) {
                throw new IllegalArgumentException("예약 시간 값이 비어 있습니다.");
            }
            dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
            appointTime = LocalDateTime.parse(postSaveReq.getAppointmentTime(), dateTimeFormatter);
            LocalDateTime now = LocalDateTime.now();
            if (appointTime.isBefore(now)) {
                throw new IllegalArgumentException("예약 시간이 과거입니다.");
            }
        }
        postRepository.save(postSaveReq.toEntity(author,appointTime));
    }
    public List<PostListRes> findAll(){
        return postRepository.findAll().stream().map(p ->p.postListFromEntity()).collect(Collectors.toList());
    }

    public Page<PostListRes> findAllPaging(Pageable pageable){
        Page<Post> pagePosts = postRepository.findAllByAppoint(pageable,"N");
        return pagePosts.map(p -> p.postListFromEntity());
    }

    public List<PostListRes> listFetchJoin(){
//        일반join: author를 join해서 post를 조회하긴 하나
        List<Post> postList = postRepository.findAllJoin();//쿼리 1qjs
//        fetch join
//        List<Post> postList = postRepository.findAllJoin();
//

        return postList.stream().map(p ->p.postListFromEntity()).collect(Collectors.toList());//쿼리 n번
    }

    public PostDetailRes findById(Long id){
        Post post = postRepository.findById(id).orElseThrow(()->new EntityNotFoundException("없다"));
        return post.toDtoFromEntity(post.getAuthor());
    }
    public void update(Long id, PostUpdateReq dto){
        Post post = postRepository.findById(id).orElseThrow(()->new EntityNotFoundException("없다"));
        post.updatePost(dto);
    }
//    public void delete(Long id){
//        Post post = postRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("없다"));
//        postRepository.delete(post);
//    }
    public void delete(Long id){
        postRepository.deleteById(id);
    }
}
