package de.metalcon.middleware.view.entity.tab.preview;

import de.metalcon.middleware.view.entity.tab.EntityTabType;

public abstract class EntityTabPreview {

    public EntityTabType entityTabType;

    public EntityTabPreview(
            EntityTabType entityTabType) {
        this.entityTabType = entityTabType;
    }

    public EntityTabType getEntityTabType() {
        return entityTabType;
    }

}
