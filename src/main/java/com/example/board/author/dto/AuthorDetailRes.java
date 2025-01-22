package com.example.board.author.dto;

import com.example.board.author.domain.Role;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class AuthorDetailRes {
    private Long id;
    private String name;
    private String email;
    private String password;
    private Role role;
    private int postCount;
    private LocalDateTime createdTime;
}
