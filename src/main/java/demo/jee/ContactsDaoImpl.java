package demo.jee;

import java.util.List;

    public class ContactsDaoImpl implements ContactsDao{
        @Override public List<String> listNames() {
            throw new RuntimeException("I need a database");
        }
    }
