package de.metalcon.middleware.controller.entity.generator.impl;

import org.springframework.stereotype.Component;

import de.metalcon.middleware.controller.entity.generator.EntityTabGenerator;
import de.metalcon.middleware.view.entity.tab.EntityTabType;

/**
 * basic about tab generator for entities
 */
@Component
public abstract class AboutTabGenerator extends EntityTabGenerator {

    public AboutTabGenerator() {
        super(EntityTabType.ABOUT);
    }

}
