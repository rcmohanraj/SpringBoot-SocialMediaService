package it.mohanrc.socialmedia.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.client.LinkDiscoverer;
import org.springframework.hateoas.client.LinkDiscoverers;
import org.springframework.hateoas.mediatype.collectionjson.CollectionJsonLinkDiscoverer;
import org.springframework.plugin.core.SimplePluginRegistry;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class HATEOASConfig {

    /*
     * HATEOAS startup error fixed because of this bean creation.
     * This startup error came because of HATEOAS + Swagger2 combination.
     * This occurs in 2.3.1 spring boot version
     * */

    @Bean
    public LinkDiscoverers discoverers() {
        List<LinkDiscoverer> linkDiscovererList = new ArrayList<>();
        linkDiscovererList.add(new CollectionJsonLinkDiscoverer());
        return new LinkDiscoverers(SimplePluginRegistry.create(linkDiscovererList));
    }
}
