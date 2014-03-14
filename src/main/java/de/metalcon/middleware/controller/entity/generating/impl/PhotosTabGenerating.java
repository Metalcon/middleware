package de.metalcon.middleware.controller.entity.generating.impl;

import de.metalcon.middleware.controller.entity.generating.EntityTabGenerating;
import de.metalcon.middleware.controller.entity.generator.impl.PhotosTabGenerator;

public interface PhotosTabGenerating extends EntityTabGenerating {

    PhotosTabGenerator getPhotosTabGenerator();

}
