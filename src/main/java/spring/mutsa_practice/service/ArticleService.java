package spring.mutsa_practice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.mutsa_practice.entity.Article;
import spring.mutsa_practice.repository.ArticleRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;

    public Long createArticle(Article article) {
        return articleRepository.save(article);
    }

    public Article findById(Long id) {
        return articleRepository.findById(id);
    }

    public List<Article> findAll() {
        return articleRepository.findAll();
    }

    public List<Article> findByTitle(String title) {
        return articleRepository.findAllByTitle(title);
    }

}
