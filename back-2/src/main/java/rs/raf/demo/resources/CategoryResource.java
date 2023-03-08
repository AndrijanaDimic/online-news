package rs.raf.demo.resources;

import rs.raf.demo.entities.Category;
import rs.raf.demo.entities.News;
import rs.raf.demo.services.CategoryService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/categories")
public class CategoryResource {
    @Inject
    private CategoryService categoryService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Category> all()
    {
        return this.categoryService.allCategories();
    }
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Category create(@Valid Category category) {
        return this.categoryService.addCategory(category);
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Category update(@Valid Category category) {
        return this.categoryService.updateCategory(category);
    }
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public void deleteCategory(@PathParam("id")  int id) {
        System.out.println(id);
        this.categoryService.deleteCategory(id);
    }

    @GET
    @Path("/{categoryId}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<News> findNews(@PathParam("categoryId")  int categoryId) {

        return this.categoryService.findNews(categoryId);

    }
}
