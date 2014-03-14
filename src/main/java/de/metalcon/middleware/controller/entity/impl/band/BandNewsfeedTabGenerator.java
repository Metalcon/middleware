package de.metalcon.middleware.controller.entity.impl.band;

import org.springframework.stereotype.Component;

import de.metalcon.middleware.controller.entity.generator.impl.NewsfeedTabGenerator;
import de.metalcon.middleware.domain.entity.Entity;
import de.metalcon.middleware.view.entity.tab.content.EntityTabContent;
import de.metalcon.middleware.view.entity.tab.preview.EntityTabPreview;

@Component
public class BandNewsfeedTabGenerator extends NewsfeedTabGenerator {

    @Override
    public void generateTabContent(EntityTabContent tabContent, Entity entity) {
        super.generateTabContent(tabContent, entity);
    }

    @Override
    public void generateTabPreview(EntityTabPreview tabPreview, Entity entity) {
        // TODO Auto-generated method stub
    }

}
