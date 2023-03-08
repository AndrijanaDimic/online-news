package rs.raf.demo.entities;

import javax.lang.model.type.ArrayType;
import java.util.ArrayList;
import java.util.List;

public class NewsList {

    List<News> news = new ArrayList<>();
    int total = 0;

    public NewsList(List<News> news, int total){
        this.news = news;
        this.total = total;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<News> getNews() {
        return news;
    }
}
