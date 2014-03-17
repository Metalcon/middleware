package de.metalcon.middleware.controller.entity.generator.impl;

import org.springframework.stereotype.Component;

import de.metalcon.middleware.controller.entity.generator.EntityTabGenerator;
import de.metalcon.middleware.view.entity.tab.EntityTabType;
import de.metalcon.middleware.view.entity.tab.content.impl.BandsTabContent;
import de.metalcon.middleware.view.entity.tab.preview.impl.BandsTabPreview;

@Component
public abstract class BandsTabGenerator extends
        EntityTabGenerator<BandsTabContent, BandsTabPreview> {

    public BandsTabGenerator() {
        super(EntityTabType.BANDS);
    }

}
