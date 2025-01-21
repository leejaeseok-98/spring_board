    package com.example.board.post.dto;

    import com.example.board.author.domain.Author;
    import com.example.board.post.domain.Post;
    import lombok.AllArgsConstructor;
    import lombok.Builder;
    import lombok.Data;
    import lombok.NoArgsConstructor;

    import javax.validation.constraints.NotEmpty;

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

        public Post toEntity(Author author){
            return Post.builder().title(this.title).contents(this.contents).author(author).build();
        }

    }
