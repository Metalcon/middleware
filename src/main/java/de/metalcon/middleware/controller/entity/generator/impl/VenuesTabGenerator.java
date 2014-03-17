package de.metalcon.middleware.controller.entity.generator.impl;

import org.springframework.stereotype.Component;

import de.metalcon.middleware.controller.entity.generator.EntityTabGenerator;
import de.metalcon.middleware.view.entity.tab.EntityTabType;
import de.metalcon.middleware.view.entity.tab.content.impl.VenuesTabContent;
import de.metalcon.middleware.view.entity.tab.preview.impl.VenuesTabPreview;

@Component
public abstract class VenuesTabGenerator extends
        EntityTabGenerator<VenuesTabContent, VenuesTabPreview> {

    public VenuesTabGenerator() {
        super(EntityTabType.VENUES);
    }

}
