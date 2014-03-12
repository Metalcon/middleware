package de.metalcon.middleware.controller.entity.tab;

import org.springframework.beans.factory.annotation.Autowired;

import de.metalcon.middleware.controller.entity.EntityController;
import de.metalcon.middleware.view.entity.tab.EntityTabType;

public abstract class EntityTabController {

    @Autowired
    protected EntityController entityController;

    public abstract EntityTabType getEntityTabType();

}
