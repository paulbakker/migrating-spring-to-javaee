package demo.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.xml.xpath.Jaxp13XPathTemplate;
import org.springframework.xml.xpath.NodeMapper;
import org.w3c.dom.DOMException;

import javax.xml.transform.Source;
import java.util.List;

@Component
public class TwitterRestClient {
    @Autowired Jaxp13XPathTemplate xpathTemplate;
    @Autowired RestTemplate restTemplate;

    public List<String> listFeed() {
        final String tweetUrl =
           "http://api.twitter.com/1/statuses/user_timeline.xml?screen_name={screen_name}";
        Source tweets = restTemplate.getForObject(tweetUrl, Source.class, "pbakker");


        List<String> timeline = xpathTemplate.evaluate("//status/text", tweets, new NodeMapper<String>() {
            @Override public String mapNode(org.w3c.dom.Node node, int i) throws DOMException {
                return node.getTextContent();
            }
        });

        return timeline;
    }
}
