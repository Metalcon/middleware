package de.metalcon.middleware.controller.entity.generator.impl;

import org.springframework.stereotype.Component;

import de.metalcon.middleware.controller.entity.generator.EntityTabGenerator;
import de.metalcon.middleware.view.entity.tab.EntityTabType;
import de.metalcon.middleware.view.entity.tab.content.impl.ReviewsTabContent;
import de.metalcon.middleware.view.entity.tab.preview.impl.RecordsTabPreview;

@Component
public abstract class RecordsTabGenerator extends
        EntityTabGenerator<ReviewsTabContent, RecordsTabPreview> {

    public RecordsTabGenerator() {
        super(EntityTabType.RECORDS);
    }

}
