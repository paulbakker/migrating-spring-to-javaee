package demo.framework;

import demo.jee.ContactsDao;

import java.util.Arrays;
import java.util.List;

@Mock
public class MockContactsDao implements ContactsDao {
    @Override public List<String> listNames() {
        return Arrays.asList("A", "B", "C");
    }
}
