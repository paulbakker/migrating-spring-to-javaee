package demo.framework;

import demo.spring.TwitterRestClient;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.enterprise.context.Dependent;
import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Default;
import javax.enterprise.inject.spi.*;
import javax.enterprise.util.AnnotationLiteral;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@SuppressWarnings({"CdiManagedBeanInconsistencyInspection"})

public class StartupListener implements Extension {
    ApplicationContext applicationContext;

    void beforeBeanDiscovery(@Observes BeforeBeanDiscovery bbd, BeanManager bm) {
        applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
    }

    void afterBeanDiscovery(@Observes AfterBeanDiscovery abd, BeanManager bm) {
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            addBean(applicationContext.getBean(beanDefinitionName).getClass(), abd, bm);
        }


    }

    private <T> void addBean(final Class<T> type, AfterBeanDiscovery abd, BeanManager bm) {
        AnnotatedType<T> at = bm.createAnnotatedType(type);

        final InjectionTarget<T> it = bm.createInjectionTarget(at);
        abd.addBean(new Bean<T>() {
            @Override public T create(CreationalContext<T> ctx) {

                T instance = applicationContext.getBean(type);
                it.inject(instance, ctx);
                it.postConstruct(instance);
                return instance;
            }

            @Override public Set<Type> getTypes() {
                Set<Type> types = new HashSet<Type>();
                types.add(type);
                types.add(Object.class);
                return types;
            }


            @Override public Set<Annotation> getQualifiers() {
                Set<Annotation> qualifiers = new HashSet<Annotation>();

                qualifiers.add(new AnnotationLiteral<Default>() {
                });

                qualifiers.add(new AnnotationLiteral<Any>() {
                });

                return qualifiers;
            }

            @Override public Class<? extends Annotation> getScope() {
                return Dependent.class;
            }

            @Override public String getName() {
                return type.getName().toLowerCase();
            }

            @Override public Set<Class<? extends Annotation>> getStereotypes() {
                return Collections.emptySet();
            }

            @Override public Class<?> getBeanClass() {
                return type;
            }

            @Override public boolean isAlternative() {
                return false;
            }

            @Override public boolean isNullable() {
                return false;
            }

            @Override public Set<InjectionPoint> getInjectionPoints() {
                return it.getInjectionPoints();
            }

            @Override
            public void destroy(T instance, CreationalContext<T> ctx) {
                it.preDestroy(instance);

                it.dispose(instance);

                ctx.release();

            }
        });
    }


}
