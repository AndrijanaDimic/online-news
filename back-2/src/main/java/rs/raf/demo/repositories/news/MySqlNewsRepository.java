package rs.raf.demo.repositories.news;

import rs.raf.demo.entities.*;
import rs.raf.demo.repositories.MySqlAbstractRepository;
import rs.raf.demo.repositories.category.CategoryRepository;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class MySqlNewsRepository extends MySqlAbstractRepository implements NewsRepository {

    public int tagId = 0;
    public int newsId = 0;
    public List<Integer> tags;

    @Override
    public News addNews(News news) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();
            String[] generatedColumns = {"newsId"};

            preparedStatement = connection.prepareStatement("INSERT INTO newss (title, content, createdDate, readings, author, categoryId) VALUES(?,?,?,?,?,?)", generatedColumns);
            preparedStatement.setString(1, news.getTitle());
            preparedStatement.setString(2, news.getContent());
            preparedStatement.setDate(3, Date.valueOf(java.time.LocalDate.now()));
            preparedStatement.setLong(4, news.getNumberOfVisitors());
            preparedStatement.setString(5, news.getAuthor());
            preparedStatement.setInt(6, news.getCategoryId());
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()) {
                news.setNewsId(resultSet.getInt(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }
        return news;
    }

    @Override
    public void deleteNews(int id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            String[] generatedColumns = {"id"};

            preparedStatement = connection.prepareStatement("DELETE FROM newss WHERE newsId=? ", generatedColumns);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()) {
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }
    }

    @Override
    public News updateNews(News news) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            String[] generatedColumns = {"newsId"};

            preparedStatement = connection.prepareStatement("UPDATE  newss SET content = ?, lastModified = ?, title = ? WHERE newsId = ? ", generatedColumns);
            //preparedStatement.setInt(1, news.getN());
            preparedStatement.setString(1, news.getContent());
            preparedStatement.setDate(2, Date.valueOf(java.time.LocalDate.now()));
            preparedStatement.setString(3, news.getTitle());
            preparedStatement.setInt(4,news.getNewsId());

            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()) {
                news.setNewsId(resultSet.getInt(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return news;
    }

    @Override
    public NewsList newsPerPage(int page, int size) {
        List<News> news = new ArrayList<>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("SELECT * FROM newss ORDER BY createdDate desc");
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int newsId = resultSet.getInt("newsId");
                String title = resultSet.getString("title");
                String content = resultSet.getString("content");
                Date date = resultSet.getDate("createdDate");
                int readings = resultSet.getInt("readings");
                String author = resultSet.getString("author");
                Date lastMod = resultSet.getDate("lastModified");
                int categoryId = resultSet.getInt("categoryId");
              news.add(new News(newsId, title, content,date, readings, author, categoryId));

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
        int max = (page + 1) * size;
        int min = (page + 1) * size - size;

        System.out.println("Ovo su min i max " + min +" " +   max);

        List<News> newRange = new ArrayList<>();

        if(news.size() <= max){
            max = news.size() - 1;
        }
        for(int i = min; i < max; i++){
            newRange.add(news.get(i));
        }
        System.out.println("nova lista je " + newRange + "size " + newRange.size());
        NewsList list= new NewsList(newRange, news.size());

        return list;
    }

    @Override
    public News findNews(int id) {
        News news = null;

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("SELECT * FROM newss where newsId = ?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                int newsId = resultSet.getInt("newsId");
                String title = resultSet.getString("title");
                String content = resultSet.getString("content");
                Date date = resultSet.getDate("createdDate");
                int readings = resultSet.getInt("readings");
                String author = resultSet.getString("author");
                Date lastMod = resultSet.getDate("lastModified");
                int categoryId = resultSet.getInt("categoryId");
                news = new News(newsId, title, content,date, readings, author, categoryId);
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

        return news;
    }

    @Override
    public NewsList findNewsByCategory(int page, int size, int categoryId) {
        List<News> news = new ArrayList<>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("SELECT * FROM newss WHERE categoryId = ? ORDER BY createdDate desc");
            preparedStatement.setInt(1, categoryId);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int newsId = resultSet.getInt("newsId");
                String title = resultSet.getString("title");
                String content = resultSet.getString("content");
                Date date = resultSet.getDate("createdDate");
                int readings = resultSet.getInt("readings");
                String author = resultSet.getString("author");
                Date lastMod = resultSet.getDate("lastModified");
                int cat = resultSet.getInt("categoryId");
                news.add(new News(newsId, title, content,date, readings, author, cat));

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
        int max = (page + 1) * size;
        int min = (page + 1) * size - size;

        System.out.println("Ovo su min i max " + min +" " +   max);

        List<News> newRange = new ArrayList<>();

        if(news.size() <= max){
            max = news.size() - 1;
        }
        for(int i = min; i <= max; i++){
            newRange.add(news.get(i));
        }
        System.out.println("nova lista je " + newRange + "size " + newRange.size());
        NewsList list= new NewsList(newRange, news.size());

        return list;
    }

    @Override
    public List<News> all() {
        List<News> news = new ArrayList<>();

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from newss");
            while (resultSet.next()) {
                int newsId = resultSet.getInt("newsId");
                String title = resultSet.getString("title");
                String content = resultSet.getString("content");
                Date date = resultSet.getDate("createdDate");
                int readings = resultSet.getInt("readings");
                String author = resultSet.getString("author");
                Date lastMod = resultSet.getDate("lastModified");
                int cat = resultSet.getInt("categoryId");
                news.add(new News(newsId, title, content,date, readings, author, cat));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(statement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return news;
    }

    @Override
    public List<News> search(String text) {
        List<News> news = new ArrayList<>();
        text .replace("!", "!!")
                .replace("%", "!%")
                .replace("_", "!_")
                .replace("[", "![");

//        text =  text + '%';
        System.out.println(text);
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("select * from newss where title like ? OR title like ? OR content like ? or content like ?");
            preparedStatement.setString(1, text + '%');
            preparedStatement.setString(2,  '%' + text);
            preparedStatement.setString(3, text + '%');
            preparedStatement.setString(4,  '%' + text);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int newsId = resultSet.getInt("newsId");
                String title = resultSet.getString("title");
                String content = resultSet.getString("content");
                Date date = resultSet.getDate("createdDate");
                int readings = resultSet.getInt("readings");
                String author = resultSet.getString("author");
                Date lastMod = resultSet.getDate("lastModified");
                int cat = resultSet.getInt("categoryId");
                news.add(new News(newsId, title, content,date, readings, author, cat));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return news;
    }

    @Override
    public NewsList newsSearch(String text, int page, int size) {
        List<News> news = new ArrayList<>();


        text .replace("!", "!!")
                .replace("%", "!%")
                .replace("_", "!_")
                .replace("[", "![");

//        text =  text + '%';
        System.out.println(text);

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("select * from newss where title like ? OR title like ? OR content like ? or content like ?");
            preparedStatement.setString(1, text + '%');
            preparedStatement.setString(2,  '%' + text);
            preparedStatement.setString(3, text + '%');
            preparedStatement.setString(4,  '%' + text);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int newsId = resultSet.getInt("newsId");
                String title = resultSet.getString("title");
                String content = resultSet.getString("content");
                Date date = resultSet.getDate("createdDate");
                int readings = resultSet.getInt("readings");
                String author = resultSet.getString("author");
                Date lastMod = resultSet.getDate("lastModified");
                int categoryId = resultSet.getInt("categoryId");
                news.add(new News(newsId, title, content,date, readings, author, categoryId));

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
        int max = (page + 1) * size;
        int min = (page + 1) * size - size;

        System.out.println("Ovo su min i max " + min +" " +   max);

        List<News> newRange = new ArrayList<>();

        if(news.size() <= max){
            max = news.size() - 1;
        }
        for(int i = min; i < max; i++){
            newRange.add(news.get(i));
        }
        System.out.println("nova lista je " + newRange + "size " + newRange.size());
        NewsList list= new NewsList(newRange, news.size());

        return list;
    }
    @Override
    public void addTag(String key, int newsId){

        if(!foundWithSameKeyWord(key)){
            addTagInTags(key);
            addTagInNews_Tag(newsId);
        }else {
            addTagInNews_Tag(newsId);
        }
    }

    public List<Integer> findTags(int newsId){

        List<Integer> tags = new ArrayList<>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("SELECT * FROM news_tag WHERE newsId LIKE ? ");
            preparedStatement.setInt(1, newsId);
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                    tags.add(resultSet.getInt("tagId"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return tags;
    }

    @Override
    public List<Tag> getTags(int newsId) {

        List<Integer> tagsIds =  findTags(newsId);

        List<Tag> tags = new ArrayList<>();


        for(int i = 0; i < tagsIds.size(); i++) {
            Connection connection = null;
            PreparedStatement preparedStatement = null;
            ResultSet resultSet = null;
            try {
                connection = this.newConnection();
                System.out.println("Po redu " + tagsIds.get(i));
                preparedStatement = connection.prepareStatement("SELECT * FROM tags WHERE tagId LIKE ? ");
                preparedStatement.setInt(1, tagsIds.get(i));
                resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    int tagId = resultSet.getInt("tagId");
                    String keyWord = resultSet.getString("keyWord");
                    tags.add(new Tag(tagId, keyWord));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                this.closeStatement(preparedStatement);
                this.closeResultSet(resultSet);
                this.closeConnection(connection);
            }
        }
        return tags;
    }

    public List<Integer> findNewsIds(int tagId){
        List<Integer> newsIds = new ArrayList<>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("SELECT * FROM news_tag WHERE tagId = ? ");
            preparedStatement.setInt(1, tagId);
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                newsIds.add(resultSet.getInt("newsId"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return newsIds;

    }

    public int findTagId(String tagName){
        int tagId = 0;

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("SELECT * FROM tags WHERE keyWord  LIKE ? ");
            preparedStatement.setString(1, tagName);
            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
               tagId = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return tagId;

    }
    @Override
    public NewsList findUsingTags(String tagName, int page, int size) {
        int tagId = findTagId(tagName);


        List<Integer> newsIds = findNewsIds(tagId);

        List<News> news = new ArrayList<>();

        for(int i = 0; i < newsIds.size(); i++) {
            Connection connection = null;
            PreparedStatement preparedStatement = null;
            ResultSet resultSet = null;
            try {
                connection = this.newConnection();
                System.out.println("Po redu " + newsIds.get(i));
                preparedStatement = connection.prepareStatement("SELECT * FROM newss WHERE newsId LIKE ? ");
                preparedStatement.setInt(1, newsIds.get(i));
                resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    int newsId = resultSet.getInt("newsId");
                    String title = resultSet.getString("title");
                    String content = resultSet.getString("content");
                    Date date = resultSet.getDate("createdDate");
                    int readings = resultSet.getInt("readings");
                    String author = resultSet.getString("author");
                    Date lastMod = resultSet.getDate("lastModified");
                    int categoryId = resultSet.getInt("categoryId");
                    news.add(new News(newsId, title, content,date, readings, author, categoryId));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                this.closeStatement(preparedStatement);
                this.closeResultSet(resultSet);
                this.closeConnection(connection);
            }
        }


        int max = (page + 1) * size;
        int min = (page + 1) * size - size;

        System.out.println("Ovo su min i max " + min +" " +   max);

        List<News> newRange = new ArrayList<>();

        if(news.size() <= max){
            max = news.size() - 1;
        }
        for(int i = min; i <= max; i++){
            newRange.add(news.get(i));
        }
        System.out.println("nova lista je " + newRange + "size " + newRange.size());
        NewsList list= new NewsList(newRange, news.size());

        return list;
    }

    @Override
    public void addReadings(int newsId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("UPDATE newss SET readings = readings + 1 WHERE newsId = ?");
            preparedStatement.setInt(1,newsId);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }
    }

    @Override
    public NewsList mostReadingsIn30days(int page, int size) {
        List<News> news = new ArrayList<>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = this.newConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM newss ORDER BY createdDate DESC, readings DESC");
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int newsId = resultSet.getInt("newsId");
                String title = resultSet.getString("title");
                String content = resultSet.getString("content");
                Date date = resultSet.getDate("createdDate");
                int readings = resultSet.getInt("readings");
                String author = resultSet.getString("author");
                Date lastMod = resultSet.getDate("lastModified");
                int categoryId = resultSet.getInt("categoryId");
                news.add(new News(newsId, title, content,date, readings, author, categoryId));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }
        int max = (page + 1) * size;
        int min = (page + 1) * size - size;

        List<News> newRange = new ArrayList<>();
        if(max >= 10){
            max = 10;
        }
        if(news.size() <= max){
            max = news.size() - 1;
        }
        for(int i = min; i < max; i++){
            newRange.add(news.get(i));
        }
        NewsList list= new NewsList(newRange, 10);

        return list;
    }

    public void addTagInTags(String keyWord){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();
            String[] generatedColumns = {"tagId"};

            preparedStatement = connection.prepareStatement("INSERT INTO tags (keyWord) VALUES(?)", generatedColumns);
            preparedStatement.setString(1, keyWord);
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();

            if(resultSet.next()) {
                this.tagId =  resultSet.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }
    }
    public void addTagInNews_Tag(int newsId){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();
            String[] generatedColumns = {"news_tagId"};

            preparedStatement = connection.prepareStatement("INSERT INTO news_tag (newsId, tagId) VALUES(?, ?)", generatedColumns);
            preparedStatement.setInt(1, newsId);
            preparedStatement.setInt(2, this.tagId);

            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();

          if(resultSet.next()){
          }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }
    }
    public boolean foundWithSameKeyWord(String key){
       boolean exists = false;

        List<News> news = new ArrayList<>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("SELECT * FROM tags WHERE keyWord LIKE ? ");
            preparedStatement.setString(1, key);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                this.tagId = resultSet.getInt("tagId");

                exists = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return exists;
    }
}

