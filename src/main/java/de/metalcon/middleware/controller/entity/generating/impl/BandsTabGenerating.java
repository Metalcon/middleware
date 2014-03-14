package de.metalcon.middleware.controller.entity.generating.impl;

import de.metalcon.middleware.controller.entity.generating.EntityTabGenerating;
import de.metalcon.middleware.controller.entity.generator.impl.BandsTabGenerator;

public interface BandsTabGenerating extends EntityTabGenerating {

    BandsTabGenerator getBandsTabGenerator();

}
