package spring.mutsa_practice.repository;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.ReflectionTestUtils;
import spring.mutsa_practice.entity.Article;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class ArticleRepositoryTest {

    @Autowired
    private ArticleRepository articleRepository;

    @AfterEach
    void tearDown(){
        articleRepository.tearDown();
    }

    @DisplayName("게시글 제목과 내용을 입력받아 게시글을 생성한다.")
    @Test
    void createArticle(){

        //given
        Article article = new Article("제목1", "내용1");

        //when
        articleRepository.save(article);
//        Article createdArticle = articleRepository.getArticleRepository().get(1L);

        Article createdArticle = articleRepository.findById(1L);

        log.info("createdArticle : " + createdArticle);

        //then
        assertThat(createdArticle)
                .extracting("title", "content")
                .containsExactlyInAnyOrder("제목1", "내용1");
    }

    @DisplayName("게시글 목록을 조회한다.")
    @Test
    void findAllArticles(){

        //given
        Article article1 = new Article("제목1", "내용1");
        Article article2 = new Article("제목2", "내용2");
        Article article3 = new Article("제목3", "내용3");

        articleRepository.save(article1);
        articleRepository.save(article2);
        articleRepository.save(article3);

        //when
        List<Article> articles = articleRepository.findAll();

        //then
        assertThat(articles).hasSize(3)
                .extracting("title")
                .containsExactlyInAnyOrder("제목1", "제목2", "제목3");
    }

    @DisplayName("게시글 ID로 게시글을 단건 조회한다.")
    @Test
    void findArticleById(){

        //given
        Article article = new Article("제목1", "내용1");

        articleRepository.save(article);

        //when
        Article savedArticle = articleRepository.findById(1L);

        //then
        assertThat(savedArticle).isEqualTo(article);

    }

    @DisplayName("게시글 제목에 포함된 단어로 게시글 검색을 할 수 있다.")
    @Test
    void searchByTitle(){

        //given
        Article article1 = new Article("제목1", "내용1");
        Article article2 = new Article("제목2", "내용2");
        Article article3 = new Article("제목3", "내용3");

        articleRepository.save(article1);
        articleRepository.save(article2);
        articleRepository.save(article3);

        //when
        List<Article> articlesWithTitle1 = articleRepository.findAllByTitle("제목");
        List<Article> articlesWithTitle2 = articleRepository.findAllByTitle("제목1");

        //then
        assertThat(articlesWithTitle1).hasSize(3);
        assertThat(articlesWithTitle2).hasSize(1)
                .extracting("title", "content")
                .containsExactly(
                        Tuple.tuple("제목1", "내용1")
                );
    }
}