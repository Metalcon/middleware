package de.metalcon.middleware.controller.entity.generating.impl;

import de.metalcon.middleware.controller.entity.generating.EntityTabGenerating;
import de.metalcon.middleware.controller.entity.generator.impl.RecordsTabGenerator;

public interface RecordsTabGenerating extends EntityTabGenerating {

    RecordsTabGenerator getRecordsTabGenerator();

}
