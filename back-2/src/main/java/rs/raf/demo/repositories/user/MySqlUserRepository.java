package rs.raf.demo.repositories.user;

import rs.raf.demo.entities.Category;
import rs.raf.demo.entities.Subject;
import rs.raf.demo.entities.User;
import rs.raf.demo.repositories.MySqlAbstractRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlUserRepository extends MySqlAbstractRepository implements UserRepository{


    @Override
    public User findUser(String username) {
        User user = null;

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();
            //String[] generatedColumns = {"id_user"};

            System.out.println("Username je" + username);
            preparedStatement = connection.prepareStatement("SELECT * FROM users where username = ?");

            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                 int userId = resultSet.getInt("id");
                 String name = resultSet.getString("name");
                 String password = resultSet.getString("password");
                 String email = resultSet.getString("email");
                 String lastName= resultSet.getString("last_name");
                 String u = resultSet.getString("username");
                 int status= resultSet.getInt("status");
                 String role= resultSet.getString("role");

                user = new User(userId, name,lastName,password,u, status,role,email);
                System.out.println("user je " + user);
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        System.out.println("user je " + user);
        return user;
    }

    @Override
    public User addUser(User user) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            String[] generatedColumns = {"id"};

            preparedStatement = connection.prepareStatement("INSERT INTO users (name,last_name, password, username, status, role,email) VALUES(?,?,?,?,?,?,?)", generatedColumns);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getHashedPassword());
            preparedStatement.setString(4, user.getUsername());
            preparedStatement.setInt(5, user.getStatus());
            preparedStatement.setString(6, user.getRole());
            preparedStatement.setString(7, user.getEmail());
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()) {
                user.setId(resultSet.getInt(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        System.out.println(user);

        return user;
    }

    @Override
    public List<User> allUsers() {
        List<User> users = new ArrayList<>();

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from users");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString( "name");
                String last_name = resultSet.getString( "last_name");
                String password = resultSet.getString("password");
                String username = resultSet.getString( "username");
                int status = resultSet.getInt("status");
                String role = resultSet.getString("role");
                String email = resultSet.getString("email");


                users.add(new User(id, name, last_name, password, username, status, role, email));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(statement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return users;
    }

    @Override
    public User updateUser(User user) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            String[] generatedColumns = {"id"};

            preparedStatement = connection.prepareStatement("UPDATE  users SET name = ?, last_name = ?, username = ? ,email = ?, role = ?, password = ? WHERE id = ? ", generatedColumns);
            //preparedStatement.setInt(1, news.getN());
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getUsername());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setString(5, user.getRole());
            preparedStatement.setString(6, user.getHashedPassword());
            preparedStatement.setInt(7, user.getId());

            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()) {
                user.setId(resultSet.getInt(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return user;
    }

    @Override
    public int changeStatus(int id, int status) {

        System.out.println("Status je sad " + status);
        if(status == 1){
            status = 0;
        }else {
            status = 1;
        }
        User user = new User();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            String[] generatedColumns = {"id"};

            preparedStatement = connection.prepareStatement("UPDATE  users SET status = ? WHERE id = ?", generatedColumns);
            //preparedStatement.setInt(1, news.getN());
            preparedStatement.setInt(2, id);
            preparedStatement.setInt(1, status);

            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()) {
               user.setId(resultSet.getInt(1));
               user.setName(resultSet.getString(2));
               user.setLastName(resultSet.getString(3));
               user.setUsername(resultSet.getString(4));
               user.setStatus(resultSet.getInt(5));
               user.setRole(resultSet.getString(6));
               user.setEmail(resultSet.getString(7));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        System.out.println("status je " + status);
        return status;
    }
}
