package com.example.board.author.dto;

import com.example.board.author.domain.Author;
import com.example.board.author.domain.Role;
import com.example.board.common.domain.BaseTimeEntity;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AuthorSaveReq {
    @NotEmpty
    private String name;
    @NotEmpty
    private String email;
    @Size(min = 8)
    @NotEmpty
    private String password;
//    사용자가 String으로 입력해도 Role클래스로 자동변환
//    ex)ADMIN,USER등으로 입력시 ENUM클래스로 변환
    private Role role = Role.USER;

    public Author toEntity(){
        return Author.builder().name(this.name).email(this.email).password(this.password).role(this.role).build();
    }
}
