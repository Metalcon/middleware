package de.metalcon.middleware.controller.entity.generator;

import de.metalcon.middleware.domain.entity.Entity;
import de.metalcon.middleware.view.entity.tab.EntityTabType;
import de.metalcon.middleware.view.entity.tab.content.EntityTabContent;
import de.metalcon.middleware.view.entity.tab.preview.EntityTabPreview;

/**
 * basic tab generator for entities
 */
public abstract class EntityTabGenerator {

    /**
     * @return tab type
     */
    public abstract EntityTabType getEntityTabType();

    /**
     * fill tab content with data
     * 
     * @param tabContent
     *            empty tab content
     * @param entity
     *            data model object to extract the data from
     */
    public abstract void generateTabContent(
            EntityTabContent tabContent,
            Entity entity);

    /**
     * fill tab preview with data
     * 
     * @param tabPreview
     *            empty tab preview
     * @param entity
     *            data model object to extract the data from
     */
    public abstract void generateTabPreview(
            EntityTabPreview tabPreview,
            Entity entity);

}
