package de.metalcon.middleware.controller.entity.generator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.metalcon.middleware.backend.newsfeedserver.NewsFeedServer;
import de.metalcon.middleware.domain.entity.Entity;
import de.metalcon.middleware.view.entity.tab.EntityTabType;
import de.metalcon.middleware.view.entity.tab.content.EntityTabContent;
import de.metalcon.middleware.view.entity.tab.content.impl.NewsfeedTabContent;

@Component
public abstract class NewsfeedTabGenerator extends EntityTabGenerator {

    @Autowired
    private NewsFeedServer nfs;

    @Override
    public EntityTabType getEntityTabType() {
        return EntityTabType.NEWSFEED;
    }

    @Override
    public void generateTabContent(EntityTabContent tabContent, Entity entity) {
        NewsfeedTabContent tc = (NewsfeedTabContent) tabContent;
        tc.setNewsFeed(nfs.getNewsFeed(entity.getMuid(), entity.getMuid(), true));
    }

}
