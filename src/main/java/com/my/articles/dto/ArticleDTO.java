package com.my.articles.dto;

import com.my.articles.entity.Article;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class ArticleDTO {
    private Long id;
    private String title;
    private String content;
    private List<CommentDto> comments = new ArrayList<>();

    public static ArticleDTO fromEntity(Article article) {
        return new ArticleDTO(
                article.getId(),
                article.getTitle(),
                article.getContent(),
                article.getComments()
                        .stream()
                        .map(x -> CommentDto.fromEntity(x))
                        .toList()
        );
    }

    //    dto->entity
    public static Article fromDto(ArticleDTO dto) {
        Article article = new Article();
        article.setId(dto.getId());
        article.setTitle(dto.getTitle());
        article.setContent(dto.getContent());
        return article;
    }
}
