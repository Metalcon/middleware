package de.metalcon.middleware.controller.entity.generating.impl;

import de.metalcon.middleware.controller.entity.generating.EntityTabGenerating;
import de.metalcon.middleware.controller.entity.generator.impl.AboutTabGenerator;

public interface AboutTabGenerating extends EntityTabGenerating {

    AboutTabGenerator getAboutTabGenerator();

}
