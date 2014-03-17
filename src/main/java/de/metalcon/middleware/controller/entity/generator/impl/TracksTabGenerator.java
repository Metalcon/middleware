package de.metalcon.middleware.controller.entity.generator.impl;

import org.springframework.stereotype.Component;

import de.metalcon.middleware.controller.entity.generator.EntityTabGenerator;
import de.metalcon.middleware.view.entity.tab.EntityTabType;
import de.metalcon.middleware.view.entity.tab.content.impl.TracksTabContent;
import de.metalcon.middleware.view.entity.tab.preview.impl.TracksTabPreview;

@Component
public abstract class TracksTabGenerator extends
        EntityTabGenerator<TracksTabContent, TracksTabPreview> {

    public TracksTabGenerator() {
        super(EntityTabType.TRACKS);
    }

}
