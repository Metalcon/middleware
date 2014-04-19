package de.metalcon.middleware.view.entity.tab.content.impl;

import java.util.List;

import de.metalcon.middleware.domain.entity.EntityData;
import de.metalcon.middleware.view.entity.tab.EntityTabType;
import de.metalcon.middleware.view.entity.tab.content.EntityTabContent;

public class RecommendationsTabContent extends EntityTabContent {

    private List<EntityData> recommendedEntities;

    public RecommendationsTabContent() {
        super(EntityTabType.RECOMMENDATIONS);

    }

    public List<EntityData> getRecommendedEntities() {
        return recommendedEntities;
    }

    public void setRecommendedEntities(List<EntityData> recommendedEntities) {
        this.recommendedEntities = recommendedEntities;
    }

}
