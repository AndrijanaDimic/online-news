package rs.raf.demo.services;

import rs.raf.demo.entities.Category;
import rs.raf.demo.entities.News;
import rs.raf.demo.entities.Subject;
import rs.raf.demo.repositories.category.CategoryRepository;
import rs.raf.demo.repositories.subject.SubjectRepository;

import javax.inject.Inject;
import java.util.List;

public class CategoryService {

    public CategoryService() {
        System.out.println(this);
    }

    @Inject
    private CategoryRepository categoryRepository;

    public Category addCategory(Category category) {
        return this.categoryRepository.addCategory(category);
    }

    public List<Category> allCategories() {
        return this.categoryRepository.allCategories();
    }
    public Category updateCategory(Category category) {
        return this.categoryRepository.updateCategory(category);
    }
    public void deleteCategory(int id) {
        this.categoryRepository.deleteCategory(id);
    }
    public List<News>findNews(int categoryId){
        return this.categoryRepository.findNews(categoryId);
    }

}
