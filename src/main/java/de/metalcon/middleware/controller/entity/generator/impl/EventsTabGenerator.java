package de.metalcon.middleware.controller.entity.generator.impl;

import org.springframework.stereotype.Component;

import de.metalcon.middleware.controller.entity.generator.EntityTabGenerator;
import de.metalcon.middleware.view.entity.tab.EntityTabType;
import de.metalcon.middleware.view.entity.tab.content.impl.EventsTabContent;
import de.metalcon.middleware.view.entity.tab.preview.EntityTabPreview;

@Component
public abstract class EventsTabGenerator extends
        EntityTabGenerator<EventsTabContent, EntityTabPreview> {

    public EventsTabGenerator() {
        super(EntityTabType.EVENTS);
    }

}
