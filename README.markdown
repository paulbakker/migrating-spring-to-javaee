Introduction
====================
This is the reference demo for the JavaOne talk "Migrating Spring to Java EE" by Bert Ertman and Paul Bakker. The demo
shows how to run Spring and Java EE side by side with the goal to slowly migrate away from Spring, without throwing away
code.

Mixing Spring and CDI
================
The demo shows how to bootstrap a Spring container in a CDI extension that publishes Spring beans to CDI. Java EE code (e.g. JSF beans) can use these Spring beans without even knowing about Spring.
The most important part is CDI extension itself: demo.framework.StartupListener.
Three Spring beans are published:
1) a JDBC Template based DAO (demo.spring.BooksJdbcDao and demo.jee.BooksBean)
2) a REST template (demo.spring.TwitterRestClient and demo.jee.TwitterTimeline)
3) a scheduled task (demo.spring.SpringTimer)


Using Spring JDBC Template from Java EE directly
=================
Because Spring JDBC Templates can be instantiated without running a Spring container they can be used in Java EE directly.
A clean way to do this is by using a CDI producer method (demo.framework.JdbcTemplateProducer and demo.jee.TemplateTester)


CDI Alternatives
=================
Mocking can be done by using CDI alternatives. demo.framework.MockContactsDao is an example.