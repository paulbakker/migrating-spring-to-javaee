package demo.framework;

import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

import javax.annotation.Resource;
import javax.enterprise.inject.Produces;
import javax.sql.DataSource;

    public class JdbcTemplateProducer {

        //Make sure you have a datasource configured in JNDI pointing to a database with a books table
        //create table book (title VARCHAR(50))
        @Resource(mappedName = "jdbc/demo") DataSource ds;

        @Produces
        public SimpleJdbcTemplate jdbcTemplate() {
            return new SimpleJdbcTemplate(ds);
        }
    }
