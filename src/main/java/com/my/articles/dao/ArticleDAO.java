package com.my.articles.dao;

import com.my.articles.entity.Article;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import nz.net.ultraq.thymeleaf.layoutdialect.models.ElementMerger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

//article Entity와 articel Table간 orm 처리종료
//dao(data access Object)
@Component
@Transactional
public class ArticleDAO {
@Autowired
EntityManager em;

    public List<Article> getALlArticles() {
        String sql = "SELECT a FROM Article AS a " +
                "ORDER BY a.id DESC";
        List<Article> articles = em.createQuery(sql)
        .getResultList();
        return articles;
    }

    public void insertArticle(Article article) {
        em.persist(article);
    }

    public Article getOneArticle(Long id) {
        return em.find(Article.class, id);
    }

    public void deleteArticle(Long id) {
//        id로 찾기
        Article article = em.find(Article.class, id);
        em.remove(article);
    }

    public void updateArticle(Article article) {
//        가져온 아티클에서 아이디로 원본 찾기
        Article original = em.find(Article.class, article.getId());
//        변경하면 끝 -더티 체킹
        original.setTitle(article.getTitle());
        original.setContent(article.getContent());
    }
}
