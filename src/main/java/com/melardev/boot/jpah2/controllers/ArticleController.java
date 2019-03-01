package com.melardev.boot.jpah2.controllers;

import com.melardev.boot.jpah2.model.Article;
import com.melardev.boot.jpah2.repositories.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/articles")
public class ArticleController {

    @Autowired
    private ArticleRepository articleRepository;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Article> index() {
        Iterable<Article> articlesIterable = articleRepository.findAll();
        List<Article> articleList = new ArrayList<>();
        articlesIterable.forEach(a -> articleList.add(a));
        return articleList;
    }

    @GetMapping(value = "/title/{value}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Article> getByTitle(@PathVariable("value") String title) {
        Iterable<Article> articlesIterable = articleRepository.findByTitleContainingIgnoreCase(title);
        List<Article> articleList = new ArrayList<>();
        articlesIterable.forEach(a -> articleList.add(a));
        return articleList;
    }

    @GetMapping(value = "/date/{after}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Article> getByDate(@PathVariable("after") @DateTimeFormat(pattern = "dd-MM-yyyy") Date date) {
        Iterable<Article> articlesIterable = articleRepository.findByCreatedAtAfter(date);
        List<Article> articleList = new ArrayList<>();
        articlesIterable.forEach(a -> articleList.add(a));
        return articleList;
    }
}
