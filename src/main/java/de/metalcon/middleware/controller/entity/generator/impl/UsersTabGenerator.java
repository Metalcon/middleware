package de.metalcon.middleware.controller.entity.generator.impl;

import org.springframework.stereotype.Component;

import de.metalcon.middleware.controller.entity.generator.EntityTabGenerator;
import de.metalcon.middleware.view.entity.tab.EntityTabType;
import de.metalcon.middleware.view.entity.tab.content.impl.UsersTabContent;
import de.metalcon.middleware.view.entity.tab.preview.impl.UsersTabPreview;

@Component
public abstract class UsersTabGenerator extends
        EntityTabGenerator<UsersTabContent, UsersTabPreview> {

    public UsersTabGenerator() {
        super(EntityTabType.USERS);
    }

}
