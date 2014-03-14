package de.metalcon.middleware.controller.entity.generating.impl;

import de.metalcon.middleware.controller.entity.generating.EntityTabGenerating;
import de.metalcon.middleware.controller.entity.generator.impl.UsersTabGenerator;

public interface UsersTabGenerating extends EntityTabGenerating {

    UsersTabGenerator getUsersTabGenerator();

}
