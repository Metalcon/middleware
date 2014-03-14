package de.metalcon.middleware.controller.entity.generating.impl;

import de.metalcon.middleware.controller.entity.generating.EntityTabGenerating;
import de.metalcon.middleware.controller.entity.generator.impl.RecommendationsTabGenerator;

public interface RecommendationsTabGenerating extends EntityTabGenerating {

    RecommendationsTabGenerator getRecommendationsTabGenerator();

}
