package rs.raf.demo.repositories.category;

import rs.raf.demo.entities.Category;
import rs.raf.demo.entities.News;

import java.util.List;

public interface CategoryRepository {
    public Category addCategory(Category category);
    public List<Category> allCategories();
    public Category updateCategory(Category category);
    public void deleteCategory(int id);
    public List<News> findNews(int categoryId);

}
