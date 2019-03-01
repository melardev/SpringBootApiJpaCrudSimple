package com.melardev.boot.jpah2.repositories;

import com.melardev.boot.jpah2.model.Article;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ArticleRepository extends CrudRepository<Article, Long> {

    public List<Article> findByTitleContainingIgnoreCase(String title);
    //public List<Article> findByTitleContains(String title);

    public List<Article> findByCreatedAtAfter(Date date);
}
