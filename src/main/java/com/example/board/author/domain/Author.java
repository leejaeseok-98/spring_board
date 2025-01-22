package com.example.board.author.domain;

import com.example.board.author.dto.AuthorDetailRes;
import com.example.board.author.dto.AuthorListRes;
import com.example.board.author.dto.AuthorUpdateReq;
import com.example.board.common.domain.BaseTimeEntity;
import com.example.board.post.domain.Post;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Builder
@Entity
public class Author extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 20, nullable = false)
    private String name;
    @Column(length = 30, nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String password;
//    enum은 기본적으로 숫자값으로 db에 들어감으로, 별도록 string지정 필요
    @Enumerated(EnumType.STRING)
    private Role role;
//    기본작ㅄ이 fetch lazy라 별도의 설정은 없음/onetomany의 기본값이 lazy임
//    mappedBydp ManyToOne쪽에 변수명을 문자열로 지정
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
//    빌더패턴에서 변수 초기화(디폴트값)시 buider.Default 어노테이션 사용
    @Builder.Default
    private List<Post> posts = new ArrayList<>();
    public void updateProfile(AuthorUpdateReq dto){
        this.name = dto.getName();
        this.password = dto.getPassword();
    }


    public AuthorListRes listDtofromEntity(){
        return AuthorListRes.builder().id(this.id).email(this.email).name(this.name).build();
    }
    public AuthorDetailRes toDetailDto(){
        return AuthorDetailRes.builder().email(this.email).name(this.name).id(this.id).password(this.password)
                .role(this.role)
                .createdTime(this.getCreateTime())
                .postCount(this.posts.size())
                .build();
    }
}
