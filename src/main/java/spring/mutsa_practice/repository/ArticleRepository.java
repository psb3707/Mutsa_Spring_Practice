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
    private Map<Long, Article> database = new HashMap<>();

    public Long save(Article article) {
        log.info("Primary ID: " + PRIMARY_ID);
        database.put(PRIMARY_ID, article);
        return PRIMARY_ID++;
    }

    public Article findById(long id) {
        return database.get(id);
    }

    public List<Article> findAll() {
        return new ArrayList<>(database.values());
    }

    public List<Article> findAllByTitle(String title) {
        List<Article> articles = new ArrayList<>();
        for (Article article : database.values()) {
            if (article.getTitle().contains(title)) {
                articles.add(article);
            }
        }
        return articles;
    }

    public void tearDown(){
        database.clear();
        PRIMARY_ID = 1;
    }
}
