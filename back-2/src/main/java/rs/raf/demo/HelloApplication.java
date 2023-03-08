package rs.raf.demo;

import com.mysql.cj.protocol.a.MysqlBinaryValueDecoder;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import rs.raf.demo.repositories.category.CategoryRepository;
import rs.raf.demo.repositories.category.MySqlCategoryRepository;
import rs.raf.demo.repositories.comments.CommentRepository;
import rs.raf.demo.repositories.comments.MySqlCommentRepository;
import rs.raf.demo.repositories.news.MySqlNewsRepository;
import rs.raf.demo.repositories.news.NewsRepository;
import rs.raf.demo.repositories.subject.InMemorySubjectRepository;
import rs.raf.demo.repositories.subject.MySqlSubjectRepository;
import rs.raf.demo.repositories.subject.SubjectRepository;
import rs.raf.demo.repositories.user.InMemoryUserRepository;
import rs.raf.demo.repositories.user.MySqlUserRepository;
import rs.raf.demo.repositories.user.UserRepository;
import rs.raf.demo.resources.CategoryResource;
import rs.raf.demo.services.*;

import javax.inject.Singleton;
import javax.ws.rs.ApplicationPath;

@ApplicationPath("/api")
public class HelloApplication extends ResourceConfig {

    public HelloApplication() {
        // Ukljucujemo validaciju
        property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true);

        // Definisemo implementacije u dependency container-u
        AbstractBinder binder = new AbstractBinder() {
            @Override
            protected void configure() {
                this.bind(MySqlSubjectRepository.class).to(SubjectRepository.class).in(Singleton.class);
                this.bind(MySqlUserRepository.class).to(UserRepository.class).in(Singleton.class);
                this.bind(MySqlCategoryRepository.class).to(CategoryRepository.class).in(Singleton.class);
                this.bind(MySqlNewsRepository.class).to(NewsRepository.class).in(Singleton.class);
                this.bind(MySqlCommentRepository.class).to(CommentRepository.class).in(Singleton.class);

                this.bindAsContract(SubjectService.class);
                this.bindAsContract(UserService.class);
                this.bindAsContract(CategoryService.class);
                this.bindAsContract(NewsService.class);
                this.bindAsContract(CommentService.class);
            }
        };
        register(binder);


        // Ucitavamo resurse
        packages("rs.raf.demo");
    }
}
