package com.example.board.post.dto;

import com.example.board.common.domain.BaseTimeEntity;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class PostDetailRes {
    private Long id;
    private String title;
    private String contents ;
    private String authorEmail ;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;
}
