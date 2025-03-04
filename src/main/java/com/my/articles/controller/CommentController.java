package com.my.articles.controller;

import com.my.articles.dto.CommentDto;
import com.my.articles.service.CommentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/articles")
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    //    댓글수정작업
    @GetMapping("comments/{id}")
    public String deleteComment(@PathVariable("id") Long id) {
        return "redirect:/articles/5";


    }

    //    댓글 입력 작업
    @PostMapping("{id}/comments")
    public String insertComment(CommentDto dto, @PathVariable("id") Long articlesId) {
        return "redirect:/articles/" + articlesId;
    }

    //    댓글 1개조회
    @GetMapping("comments/view/{commentId}")
    public String updateCommentView() {
//        댓글 읽어와서 view에 전달하기
        return "/articles/update_comment";

    }

    //    댓글수정
    @PostMapping("{articleId}/comments/{id}")
    public String updateComment(
            @PathVariable("articleId")Long articleId,
            @PathVariable("commentId")Long commentId
    ) {
        return "redirect:/articles/" + articleId;
    }

}
