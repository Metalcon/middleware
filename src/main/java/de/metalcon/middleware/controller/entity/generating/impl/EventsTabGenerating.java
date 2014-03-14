package de.metalcon.middleware.controller.entity.generating.impl;

import de.metalcon.middleware.controller.entity.generating.EntityTabGenerating;
import de.metalcon.middleware.controller.entity.generator.impl.EventsTabGenerator;

public interface EventsTabGenerating extends EntityTabGenerating {

    EventsTabGenerator getEventsTabGenerator();

}
