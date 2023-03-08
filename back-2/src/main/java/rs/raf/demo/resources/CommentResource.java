package rs.raf.demo.resources;

import rs.raf.demo.entities.Comment;
import rs.raf.demo.services.CommentService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/comments")
public class CommentResource {
    @Inject
    private CommentService commentService;

    @POST
    @Produces(MediaType.APPLICATION_JSON)

    public Comment create(@Valid Comment comment
    ) {
        System.out.println("okejj");
        System.out.println("Novostii" + comment);
        return this.commentService.addComments(comment);
    }
    @GET
    @Path("/{newsId}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Comment> all(@PathParam("newsId") int newsId)
    {
       // System.out.println("Novostii" + newsId);
        return this.commentService.allComments(newsId);
    }
}
