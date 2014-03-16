package de.metalcon.middleware.controller.entity.generator.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.metalcon.middleware.backend.newsserver.NewsServer;
import de.metalcon.middleware.controller.entity.generator.EntityTabGenerator;
import de.metalcon.middleware.domain.entity.Entity;
import de.metalcon.middleware.view.entity.tab.EntityTabType;
import de.metalcon.middleware.view.entity.tab.content.EntityTabContent;
import de.metalcon.middleware.view.entity.tab.content.impl.NewsTabContent;

@Component
public abstract class NewsTabGenerator extends EntityTabGenerator {

    @Autowired
    private NewsServer newsServer;

    public NewsTabGenerator() {
        super(EntityTabType.NEWS);
    }

    @Override
    public void generateTabContent(EntityTabContent tabContent, Entity entity) {
        NewsTabContent tc = (NewsTabContent) tabContent;
        tc.setNews(newsServer.getNews(entity.getMuid(), entity.getMuid(), true));
    }

}
