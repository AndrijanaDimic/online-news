package rs.raf.demo.resources;

import org.apache.commons.codec.digest.DigestUtils;
import rs.raf.demo.entities.User;
import rs.raf.demo.requests.LoginRequest;
import rs.raf.demo.services.UserService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Path("/users")
public class UserResource {

    @Inject
    private UserService userService;

    @POST
    @Path("/login")
    @Produces({MediaType.APPLICATION_JSON})
    public Response login(@Valid LoginRequest loginRequest)
    {
        Map<String, String> response = new HashMap<>();
        System.out.println("Login request " + loginRequest.getUsername());
        String jwt = this.userService.login(loginRequest.getUsername(), loginRequest.getPassword());
        if (jwt == null) {
            response.put("message", "These credentials do not match our records");
            return Response.status(422, "Unprocessable Entity").entity(response).build();
        }

        response.put("jwt", jwt);
        return Response.ok(response).build();
    }
    @GET
    @Path("/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public User find(@PathParam("username") String username) {
        return this.userService.findUser(username);
    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> all()
    {
        return this.userService.allUsers();
    }
    @POST
    @Path("/register")
    @Produces({MediaType.APPLICATION_JSON})
    public Response register(@Valid User user)
    {
        Map<String, String> response = new HashMap<>();

//        String jwt = this.userService.register(user.getUsername(), user.getRole());
//        if (jwt == null) {
//            response.put("message", "These credentials do not match our records");
//            return Response.status(422, "Unprocessable Entity").entity(response).build();
//        }
//
//        response.put("jwt", jwt);
        System.out.println( "Pass je : " + user.getHashedPassword());

        user.setHashedPassword( DigestUtils.sha256Hex(user.getHashedPassword()));
        this.userService.addUser(user);

        return Response.ok(response).build();
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public User update(@Valid User user) {
        System.out.println(user);
        user.setHashedPassword( DigestUtils.sha256Hex(user.getHashedPassword()));
        return this.userService.updateUser(user);
    }

    @PUT
    @Path("/status")
    @Produces(MediaType.APPLICATION_JSON)
    public int changeStatus(User user) {
        int id = user.getId();
        int status = user.getStatus();

        return this.userService.changeStatus(id, status);
    }



}
