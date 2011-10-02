package demo.jee;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import java.util.List;

    @Model
    public class ContactsBean {
        @Inject ContactsDao contactsDao;

        public List<String> getContactList() {
            return contactsDao.listNames();
        }
    }
