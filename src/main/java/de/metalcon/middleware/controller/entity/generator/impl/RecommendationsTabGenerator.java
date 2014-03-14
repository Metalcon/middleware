package de.metalcon.middleware.controller.entity.generator.impl;

import org.springframework.stereotype.Component;

import de.metalcon.middleware.controller.entity.generator.EntityTabGenerator;
import de.metalcon.middleware.view.entity.tab.EntityTabType;

@Component
public abstract class RecommendationsTabGenerator extends EntityTabGenerator {

    public RecommendationsTabGenerator() {
        super(EntityTabType.RECOMMENDATIONS);
    }

}
