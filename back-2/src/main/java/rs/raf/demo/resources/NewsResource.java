package rs.raf.demo.resources;


import rs.raf.demo.entities.News;
import rs.raf.demo.entities.NewsList;
import rs.raf.demo.entities.Tag;
import rs.raf.demo.services.NewsService;

import javax.inject.Inject;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/news")
public class NewsResource {

    @Inject
    private NewsService newsService;

    @POST
    @Produces(MediaType.APPLICATION_JSON)

    public News create(@Valid News news) {
        return this.newsService.addNews(news);
    }
    @GET
    @Path("/tags/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Tag> getTags(@PathParam("id") int newsId) {

        return this.newsService.getTags(newsId);
    }
    @GET
    @Path("/tagName")
    @Produces(MediaType.APPLICATION_JSON)
    public NewsList getTags(@QueryParam("tagName") String tagName,@QueryParam("size") int size, @QueryParam("page") int page ) {

        return this.newsService.findUsingTags(tagName, page, size);
    }

    @GET
    @Path("/addTag")
    @Produces(MediaType.APPLICATION_JSON)
    public void addTag(@QueryParam("key") String key, @QueryParam("newsId") int newsId) {
        this.newsService.addTag(key, newsId);
    }
    @POST
    @Path("/addReadings/{newsId}")
    @Produces(MediaType.APPLICATION_JSON)
    public void addReadings(@PathParam("newsId") int newsId) { ;
        this.newsService.addReadings(newsId);
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public void deleteCategory(@PathParam("id")  int id) {
        this.newsService.deleteNews(id);
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public News update(@Valid News news) {

        return this.newsService.updateNews(news);
    }
    @GET
    @Path("/mostReadingsIn30days")
    @Produces(MediaType.APPLICATION_JSON)
    public NewsList mostReadings(@QueryParam("size") int size, @QueryParam("page") int page ) {

        return this.newsService.mostReadings(page,size);
    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public NewsList itemName(@QueryParam("size") int size, @QueryParam("page") int page ) {

        return this.newsService.newsPerPage(page, size);
    }
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public News findNews(@PathParam("id")  int id) {
        return this.newsService.findNews(id);
    }
    @GET
    @Path("/category/{categoryId}")
    @Produces(MediaType.APPLICATION_JSON)
    public NewsList findNews(@PathParam("categoryId") int categoryId, @QueryParam("size") int size, @QueryParam("page") int page ) {
        return this.newsService.findNewsByCategory(page, size, categoryId);

    }
    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<News> all() {
        return this.newsService.allNews();
    }
    @GET
    @Path("/search")
    @Produces(MediaType.APPLICATION_JSON)
    public List<News> search(@QueryParam("text")  String text) {
        return this.newsService.search(text);
    }
    @GET
    @Path("/newsSearch")
    @Produces(MediaType.APPLICATION_JSON)
    public NewsList search(@QueryParam("text") String text, @QueryParam("size") int size, @QueryParam("page") int page ) {

        return this.newsService.newsSearch(text, page, size);
    }


}
