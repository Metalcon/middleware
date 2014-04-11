package de.metalcon.middleware.controller.entity.tab;

import de.metalcon.middleware.controller.entity.EntityController;
import de.metalcon.middleware.view.entity.tab.EntityTabType;

public abstract class EntityTabController {

    private EntityTabType entityTabType;

    public EntityTabController(
            EntityTabType entityTabType) {
        this.entityTabType = entityTabType;
    }

    public EntityTabType getEntityTabType() {
        return entityTabType;
    }

    public void handleGet(
            EntityController.Data data,
            EntityController<?> entityController) {
    }

}
