package demo.jee;

import demo.spring.BooksJdbcDao;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
@RequestScoped
public class BooksBean {
    @Inject BooksJdbcDao booksDao;

    public List<String> getTitles() {
        System.out.println("changed again and again?");
        return booksDao.listBookTitles();
    }
}
