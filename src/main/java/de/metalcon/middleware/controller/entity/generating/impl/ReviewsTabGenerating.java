package de.metalcon.middleware.controller.entity.generating.impl;

import de.metalcon.middleware.controller.entity.generating.EntityTabGenerating;
import de.metalcon.middleware.controller.entity.generator.impl.ReviewsTabGenerator;

public interface ReviewsTabGenerating extends EntityTabGenerating {

    ReviewsTabGenerator getReviewsTabGenerator();

}
