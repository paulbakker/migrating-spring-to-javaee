package demo.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class BooksJdbcDao {
    private SimpleJdbcTemplate simpleJdbcTemplate;

    public List<String> listBookTitles() {
        return simpleJdbcTemplate.query("select title from book", new RowMapper<String>() {
            @Override
            public String mapRow(ResultSet resultSet, int i) throws SQLException {
                return resultSet.getString("title");
            }
        });
    }

    @Autowired
    public void setDataSource(DataSource ds) {
        simpleJdbcTemplate = new SimpleJdbcTemplate(ds);
    }

    @PostConstruct
    public void initDB() {
        simpleJdbcTemplate.update("drop table book if exists");
        simpleJdbcTemplate.update("create table book (title VARCHAR(50))");
        simpleJdbcTemplate.update("INSERT INTO book (title) values ('The Da Vinci Code')");
        simpleJdbcTemplate.update("INSERT INTO book (title) values ('Angels and Demons')");
        simpleJdbcTemplate.update("INSERT INTO book (title) values ('The Lost Symbol')");
        simpleJdbcTemplate.update("INSERT INTO book (title) values ('Digital Fortress')");
    }
}
