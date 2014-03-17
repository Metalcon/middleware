package de.metalcon.middleware.controller.entity.generator.impl;

import org.springframework.stereotype.Component;

import de.metalcon.middleware.controller.entity.generator.EntityTabGenerator;
import de.metalcon.middleware.view.entity.tab.EntityTabType;
import de.metalcon.middleware.view.entity.tab.content.impl.RecommendationsTabContent;
import de.metalcon.middleware.view.entity.tab.preview.impl.RecommendationsTabPreview;

@Component
public abstract class RecommendationsTabGenerator
        extends
        EntityTabGenerator<RecommendationsTabContent, RecommendationsTabPreview> {

    public RecommendationsTabGenerator() {
        super(EntityTabType.RECOMMENDATIONS);
    }

}
