package rs.raf.demo.repositories.user;

import rs.raf.demo.entities.User;

import java.util.List;
import java.util.ListResourceBundle;

public interface UserRepository {
    public User findUser(String username);
    public User addUser(User user);
    public List<User> allUsers();
    public User updateUser(User user);
    public int changeStatus(int id, int status);
}
