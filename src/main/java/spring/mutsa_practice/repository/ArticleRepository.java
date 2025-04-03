package spring.mutsa_practice.repository;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import spring.mutsa_practice.entity.Article;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@Getter
@Slf4j
public class ArticleRepository {

    private long PRIMARY_ID = 1;

    //DB
    private Map<Long, Article> articleRepository = new HashMap<>();

    public Long save(Article article) {
        log.info("Primary ID: " + PRIMARY_ID);
        articleRepository.put(PRIMARY_ID, article);
        return PRIMARY_ID++;
    }

    public Article findById(long id) {
        return articleRepository.get(id);
    }

    public List<Article> findAll() {
        return new ArrayList<>(articleRepository.values());
    }

    public List<Article> findAllByTitle(String title) {
        List<Article> articles = new ArrayList<>();
        for (Article article : articleRepository.values()) {
            if (article.getTitle().contains(title)) {
                articles.add(article);
            }
        }
        return articles;
    }

    public void tearDown(){
        articleRepository.clear();
        PRIMARY_ID = 1;
    }
}
