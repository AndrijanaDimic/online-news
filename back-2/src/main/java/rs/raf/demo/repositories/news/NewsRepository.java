package rs.raf.demo.repositories.news;

import rs.raf.demo.entities.Category;
import rs.raf.demo.entities.News;
import rs.raf.demo.entities.NewsList;
import rs.raf.demo.entities.Tag;

import java.util.List;

public interface NewsRepository {

    public News addNews(News news);
    public void deleteNews(int id);
    public News updateNews(News news);
    public NewsList newsPerPage(int page, int size);
    public News findNews(int id);
    public NewsList findNewsByCategory(int page, int size, int categoryId);
    public List<News> all();
    public List<News> search(String text);
    public NewsList newsSearch(String text, int page, int size);
    public void addTag(String key, int newsId);
    public List<Tag> getTags(int newsId);
    public NewsList findUsingTags(String tagName, int page, int size);
    public void addReadings(int newsId);
    public NewsList mostReadingsIn30days(int page, int size);
}
