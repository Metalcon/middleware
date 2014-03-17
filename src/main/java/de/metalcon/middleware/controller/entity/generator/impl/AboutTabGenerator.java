package de.metalcon.middleware.controller.entity.generator.impl;

import org.springframework.stereotype.Component;

import de.metalcon.middleware.controller.entity.generator.EntityTabGenerator;
import de.metalcon.middleware.view.entity.tab.EntityTabType;
import de.metalcon.middleware.view.entity.tab.content.impl.AboutTabContent;
import de.metalcon.middleware.view.entity.tab.preview.impl.AboutTabPreview;

/**
 * basic about tab generator for entities
 */
@Component
public abstract class AboutTabGenerator extends
        EntityTabGenerator<AboutTabContent, AboutTabPreview> {

    public AboutTabGenerator() {
        super(EntityTabType.ABOUT);
    }

}
