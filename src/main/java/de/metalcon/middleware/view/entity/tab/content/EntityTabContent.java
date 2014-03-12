package de.metalcon.middleware.view.entity.tab.content;

import de.metalcon.middleware.view.entity.tab.EntityTabType;

/**
 * basic container class for entity tab content<br>
 * (getter/setter of data displayed)
 */
public abstract class EntityTabContent {

    /**
     * @return tab type on this entity page
     */
    public abstract EntityTabType getEntityTabType();

}
