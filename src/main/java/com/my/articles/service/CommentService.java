package com.my.articles.service;

import com.my.articles.dao.CommentDAO;
import com.my.articles.dto.CommentDto;
import com.my.articles.entity.Comment;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class CommentService {
    private final CommentDAO commentDAO;

    public CommentService(CommentDAO commentDAO) {
        this.commentDAO = commentDAO;
    }

    public Long deleteComment(Long id) {
        return commentDAO.deleteComment(id);
    }

    public void insertComment(Long articleId, CommentDto dto) {
     commentDAO.insertComment(articleId,CommentDto.fromDto(dto));
    }

    public Map<String, Object> findByCommentId(Long commentId) {
        Comment comment = commentDAO.findByCommentId(commentId);
        Map<String, Object> data = new HashMap<>();
        if (ObjectUtils.isEmpty(comment)) {
            data.put("articleID", null);
            data.put("dto",null);

        }else {
            data.put("articleID",comment.getArticle().getId());
            data.put("dto",CommentDto.fromEntity(comment))  ;
        }
        return data;
    }

    public void updateComment(CommentDto dto) {
        commentDAO.updateComment(CommentDto.fromDto(dto));
    }
}
