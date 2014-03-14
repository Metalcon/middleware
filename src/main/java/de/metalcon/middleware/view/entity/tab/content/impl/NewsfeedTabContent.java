package de.metalcon.middleware.view.entity.tab.content.impl;

import java.util.HashMap;
import java.util.Map;

import de.metalcon.middleware.view.entity.tab.EntityTabType;
import de.metalcon.middleware.view.entity.tab.content.EntityTabContent;

public class NewsfeedTabContent extends EntityTabContent {

    private Map<String, Object> newsFeed = new HashMap<String, Object>();

    public NewsfeedTabContent() {
        super(EntityTabType.NEWSFEED);
    }

    public void setNewsFeed(Map<String, Object> newsFeed) {
        this.newsFeed = newsFeed;
    }

    public Map<String, Object> getNewsFeed() {
        return newsFeed;
    }
}
