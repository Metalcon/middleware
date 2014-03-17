package de.metalcon.middleware.view.entity.tab.content.impl;

import java.util.Map;

import de.metalcon.middleware.view.entity.tab.EntityTabType;
import de.metalcon.middleware.view.entity.tab.content.EntityTabContent;

public class NewsTabContent extends EntityTabContent {

    private Map<String, Object> news;

    public NewsTabContent() {
        super(EntityTabType.NEWS);
    }

    public void setNews(Map<String, Object> news) {
        this.news = news;
    }

    public Map<String, Object> getNews() {
        return news;
    }
}
