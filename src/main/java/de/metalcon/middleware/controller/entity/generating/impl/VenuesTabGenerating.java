package de.metalcon.middleware.controller.entity.generating.impl;

import de.metalcon.middleware.controller.entity.generating.EntityTabGenerating;
import de.metalcon.middleware.controller.entity.generator.impl.VenuesTabGenerator;

public interface VenuesTabGenerating extends EntityTabGenerating {

    VenuesTabGenerator getVenuesTabGenerator();

}
