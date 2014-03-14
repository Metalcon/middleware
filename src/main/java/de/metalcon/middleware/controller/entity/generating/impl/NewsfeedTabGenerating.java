package de.metalcon.middleware.controller.entity.generating.impl;

import de.metalcon.middleware.controller.entity.generating.EntityTabGenerating;
import de.metalcon.middleware.controller.entity.generator.impl.NewsfeedTabGenerator;

public interface NewsfeedTabGenerating extends EntityTabGenerating {

    NewsfeedTabGenerator getNewsfeedTabGenerator();

}
