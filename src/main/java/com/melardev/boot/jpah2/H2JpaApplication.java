package com.melardev.boot.jpah2;

import com.melardev.boot.jpah2.model.Article;
import com.melardev.boot.jpah2.repositories.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootApplication
public class H2JpaApplication {

    public static void main(String[] args) {
        SpringApplication.run(H2JpaApplication.class, args);
    }

    @Bean
    @Autowired
    CommandLineRunner runner(ArticleRepository articleRepository) {
        return (args) -> {
            long count = articleRepository.count();

            if (count == 0) {
                SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");

                try {
                    Article article1 = new Article();
                    article1.setTitle("Spring Boot and JPA");
                    article1.setBody("How to use Spring Data JPA with H2 in Spring Boot");
                    Date date = df.parse("10/5/2018");
                    article1.setCreatedAt(date);

                    //
                    Article article2 = new Article();
                    article2.setTitle("Spring Boot");
                    article2.setBody("How to create Spring Boot projects");
                    date = df.parse("2/3/2017");
                    article2.setCreatedAt(date);

                    articleRepository.save(article1);
                    articleRepository.save(article2);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        };
    }
}
