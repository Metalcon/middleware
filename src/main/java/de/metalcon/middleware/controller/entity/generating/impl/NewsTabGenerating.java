package de.metalcon.middleware.controller.entity.generating.impl;

import de.metalcon.middleware.controller.entity.generating.EntityTabGenerating;
import de.metalcon.middleware.controller.entity.generator.impl.NewsTabGenerator;

public interface NewsTabGenerating extends EntityTabGenerating {

    NewsTabGenerator getNewsTabGenerator();

}
