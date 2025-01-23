package com.example.board.post.domain;

import com.example.board.author.domain.Author;
import com.example.board.common.domain.BaseTimeEntity;
import com.example.board.post.dto.PostDetailRes;
import com.example.board.post.dto.PostListRes;
import com.example.board.post.dto.PostUpdateReq;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Post extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 50, nullable = false)
    private String title;
    @Column(length = 3000)
    private String contents;
    private LocalDateTime appointmentTime;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private Author author;

    public void updatePost(PostUpdateReq dto){
        this.title = dto.getTitle();
        this.contents = dto.getContents();
    }

    public PostListRes postListFromEntity(){
        return PostListRes.builder().id(this.id).title(this.title).authorEmail(this.author.getEmail()).build();
    }
    public PostDetailRes toDtoFromEntity(Author author){
        return PostDetailRes.builder()
                .id(this.id)
                .title(this.title)
                .contents(this.contents)
                .authorEmail(this.author.getEmail())
                .createdTime(this.getCreateTime())
                .updatedTime(this.getUpdateTime())
                .build();
    }
}
