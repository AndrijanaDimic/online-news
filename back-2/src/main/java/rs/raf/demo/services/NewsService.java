package rs.raf.demo.services;

import rs.raf.demo.entities.*;
import rs.raf.demo.repositories.category.CategoryRepository;
import rs.raf.demo.repositories.news.NewsRepository;

import javax.inject.Inject;
import java.util.List;

public class NewsService {

    @Inject
    private NewsRepository newsRepository;

    public News addNews(News news) {
        return this.newsRepository.addNews(news);
    }
    public void deleteNews(int id){this.newsRepository.deleteNews(id);}

    public News updateNews(News news) {
        return this.newsRepository.updateNews(news);
    }
    public NewsList newsPerPage(int page, int size) {
        return this.newsRepository.newsPerPage(page, size);
    }
    public News findNews(int id) {return this.newsRepository.findNews(id);}
    public NewsList findNewsByCategory(int page, int size, int categoryId) {
        return this.newsRepository.findNewsByCategory(page, size, categoryId);
    }
    public List<News> allNews(){
        return this.newsRepository.all();
    }
    public List<News> search(String text){
        return this.newsRepository.search( text);
    }
    public NewsList newsSearch(String text, int page, int size){
        return this.newsRepository.newsSearch(text, page, size);
    }
    public NewsList findUsingTags(String tagName, int page, int size){
        return this.newsRepository.findUsingTags(tagName, page, size);
    }

    public void addTag(String key, int newsId){
        this.newsRepository.addTag(key,newsId);
    }
    public List<Tag> getTags(int newsId){
       return this.newsRepository.getTags(newsId);
    }
    public void addReadings(int newsId){
        this.newsRepository.addReadings(newsId);
    }
    public NewsList mostReadings(int page, int size){
       return this.newsRepository.mostReadingsIn30days(page, size);
    }

}
