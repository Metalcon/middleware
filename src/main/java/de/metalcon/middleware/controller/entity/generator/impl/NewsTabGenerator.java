package de.metalcon.middleware.controller.entity.generator.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.metalcon.domain.Muid;
import de.metalcon.middleware.backend.newsserver.NewsServer;
import de.metalcon.middleware.controller.entity.EntityController;
import de.metalcon.middleware.controller.entity.generator.EntityTabGenerator;
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
    public NewsTabContent generateTabContent(final EntityController.Data data) {
        Muid muid = data.getMuid();
        NewsTabContent tabContent = super.generateTabContent(data);
        tabContent.setNews(newsServer.getNews(muid, muid, true));
        return tabContent;
    }
}
