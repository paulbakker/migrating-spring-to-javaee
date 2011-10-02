package demo.jee;

import demo.spring.TwitterRestClient;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@RequestScoped
@Named
public class TwitterTimeline {
    @Inject TwitterRestClient twitterRestClient;

    public List<String> getTimeline() {
        return twitterRestClient.listFeed();
    }
}
