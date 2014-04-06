package de.metalcon.middleware.view.entity.tab.content;

import de.metalcon.middleware.view.entity.tab.EntityTabType;

/**
 * basic container class for entity tab content<br>
 * (getter/setter of data displayed)
 */
public abstract class EntityTabContent {

    private EntityTabType entityTabType;

    private String entityName;

    public EntityTabContent(
            EntityTabType entityTabType) {
        this.entityTabType = entityTabType;
    }

    /**
     * @return tab type on this entity page
     */
    public EntityTabType getEntityTabType() {
        return entityTabType;
    }

    public void setName(String name) {
        entityName = name;
    }

}
