package de.metalcon.middleware.controller.entity.generating.impl;

import de.metalcon.middleware.controller.entity.generating.EntityTabGenerating;
import de.metalcon.middleware.controller.entity.generator.impl.TracksTabGenerator;

public interface TracksTabGenerating extends EntityTabGenerating {

    TracksTabGenerator getTracksTabGenerator();

}
