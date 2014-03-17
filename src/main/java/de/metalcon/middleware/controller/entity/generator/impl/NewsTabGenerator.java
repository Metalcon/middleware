package de.metalcon.middleware.controller.entity.generator.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.metalcon.middleware.backend.newsserver.NewsServer;
import de.metalcon.middleware.controller.entity.generator.EntityTabGenerator;
import de.metalcon.middleware.domain.entity.Entity;
import de.metalcon.middleware.view.entity.tab.EntityTabType;
import de.metalcon.middleware.view.entity.tab.content.impl.NewsTabContent;
import de.metalcon.middleware.view.entity.tab.preview.impl.NewsTabPreview;

@Component
public abstract class NewsTabGenerator extends
        EntityTabGenerator<NewsTabContent, NewsTabPreview> {

    @Autowired
    private NewsServer newsServer;

    public NewsTabGenerator() {
        super(EntityTabType.NEWS);
    }

    @Override
    public NewsTabContent generateTabContent(Entity entity) {
        NewsTabContent tabContent = super.generateTabContent(entity);
        tabContent.setNews(newsServer.getNews(entity.getMuid(),
                entity.getMuid(), true));
        return tabContent;
    }

}
