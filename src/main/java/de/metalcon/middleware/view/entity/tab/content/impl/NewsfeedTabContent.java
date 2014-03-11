package de.metalcon.middleware.view.entity.tab.content.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import de.metalcon.middleware.backend.newsfeedserver.NewsFeedServer;
import de.metalcon.middleware.view.entity.tab.EntityTabType;
import de.metalcon.middleware.view.entity.tab.content.EntityTabContent;

public class NewsfeedTabContent extends EntityTabContent {

    @Autowired
    private NewsFeedServer nfs;

    @Override
    public EntityTabType getEntityTabType() {
        return EntityTabType.NEWSFEED;
    }

    public Map<String, Object> getNewsFeed() {
        return nfs.getNewsFeed("3", "3", true);
    }
}
