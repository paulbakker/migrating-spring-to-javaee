package demo.jee;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Model
public class TemplateTester {

    @Inject SimpleJdbcTemplate template;

     public List<String> getBookTitles() {
        return template.query("select title from book", new RowMapper<String>() {
            @Override public String mapRow(ResultSet resultSet, int i) throws SQLException {
                return resultSet.getString("titel");
            }
        });
    }



}
