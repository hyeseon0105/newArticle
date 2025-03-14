package com.my.articles.api.controller;

import com.my.articles.api.exception.ApiResponseMessage;
import com.my.articles.api.exception.BadRequestException;
import com.my.articles.dto.ArticleDTO;
import com.my.articles.dto.CommentDto;
import com.my.articles.service.ArticleService;
import com.my.articles.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Objects;

@RestController
//controller=@reponseVBody를 결합해놓음.

public class CommentApiController {
    private final CommentService commentService;

    private final ArticleService articleService;

    public CommentApiController(CommentService commentService, ArticleService articleService) {
        this.commentService = commentService;
        this.articleService = articleService;
    }
    @GetMapping("/api/exception")
    public ResponseEntity<?>  exceptionHandler(){
        throw new BadRequestException("예외처리테스트");
    }

    //    1.댓글조회
    @GetMapping("api/comments/{commentId}")
    public ResponseEntity<?> commentSearch(
            @PathVariable("commentId")Long commentId
    ) {
        CommentDto findComment = getDto(commentId,"댓글 조회에 실패했습니다.");
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(findComment);
    }

    //    2.댓글생성
    @PostMapping("/api/articles/{articleId}/comments")
    public ResponseEntity<?> commentCreate
    (@PathVariable("articleId") Long articleId,
     @RequestBody CommentDto dto) {
//        던져진 아티클아이디가 존재하는지 확인
        ArticleDTO existsArticle = articleService.getOneArticle(articleId);
//        없는 게시글이면 exception처리
        if (ObjectUtils.isEmpty(existsArticle)) {
            throw new BadRequestException("게시글 Id가 존재하지 않습니다");
        }else {
            //        존재한다면 댓글 추가하기
            commentService.insertComment(articleId,dto);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(ApiResponseMessage
                            .builder()
                            .message("댓글 생성 성공")
                            .build());
        }

    }

    //    3.댓글수정
    @PatchMapping("/api/comments/{commentId}")
    public ResponseEntity<?> commentUpdate(
            @PathVariable("commentId")Long commentId,
                                            @RequestBody CommentDto dto)
    {
//        URL의 commentId와 RequestBody의  commentsId가 동일한지 확인
        if (!dto.getCommentId().equals(commentId)) {
            throw new BadRequestException("댓글 수정 요청 잘못 오류");
        }
//       수정요청 커멘트 아이디가 존재하는지 확인
        CommentDto result = getDto(commentId,"댓글 수정 실패");
//        존재하면 수정요청
        commentService.updateComment(dto);
       return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponseMessage
                        .builder()
                        .message("댓글 수정 성공")
                        .build());
    }
//    4.댓글삭제
    @DeleteMapping("/api/comments/{commentId}")
    public ResponseEntity<?> commentDelete(
            @PathVariable("commentId") Long commentId) {

//        삭제할 댓글 아이디 확인
        CommentDto result = getDto(commentId,"댓글 삭제 실패");

//        삭제요청하기
        commentService.deleteComment(result.getCommentId());
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponseMessage
                        .builder()
                        .message("댓글 삭제 성공")
                        .build());

    }

    private CommentDto getDto(Long commentId,String message) {
        Map<String, Object> findComment =
                commentService.findByCommentId(commentId);
        if (ObjectUtils.isEmpty(findComment.get("dto"))) {
            throw new BadRequestException(message);
        }
        return (CommentDto) findComment.get("dto");
    }

}
