    package com.example.board.post.dto;

    import com.example.board.author.domain.Author;
    import com.example.board.post.domain.Post;
    import lombok.AllArgsConstructor;
    import lombok.Builder;
    import lombok.Data;
    import lombok.NoArgsConstructor;
    import org.springframework.format.annotation.DateTimeFormat;

    import javax.validation.constraints.NotEmpty;
    import java.time.LocalDateTime;

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    @Builder
    public class PostSaveReq {
        @NotEmpty
        private String title;
        @NotEmpty
        private String email;
        private String contents;
        private String appoint;
        private String appointmentTime;


        public Post toEntity(Author author,LocalDateTime appointmentTime){
            return Post.builder().title(this.title).contents(this.contents).author(author).appointmentTime(appointmentTime)
                    .appoint(this.appoint)
                    .build();
        }

    }
