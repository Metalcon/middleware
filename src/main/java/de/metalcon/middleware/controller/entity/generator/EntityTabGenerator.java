package de.metalcon.middleware.controller.entity.generator;

import org.springframework.beans.factory.annotation.Autowired;

import de.metalcon.domain.Muid;
import de.metalcon.middleware.view.entity.tab.EntityTabType;
import de.metalcon.middleware.view.entity.tab.content.EntityTabContent;
import de.metalcon.middleware.view.entity.tab.content.EntityTabContentFactory;
import de.metalcon.middleware.view.entity.tab.preview.EntityTabPreview;
import de.metalcon.middleware.view.entity.tab.preview.EntityTabPreviewFactory;

/**
 * basic tab generator for entities
 */
public abstract class EntityTabGenerator<EntityTabContentType extends EntityTabContent, EntityTabPreviewType extends EntityTabPreview > {

    @Autowired
    private EntityTabContentFactory entityTabContentFactory;

    @Autowired
    private EntityTabPreviewFactory entityTabPreviewFactory;

    private EntityTabType entityTabType;

    public EntityTabGenerator(
            EntityTabType entityTabType) {
        this.entityTabType = entityTabType;
    }

    /**
     * @return tab type
     */
    public EntityTabType getEntityTabType() {
        return entityTabType;
    }

    /**
     * fill tab content with data
     * 
     * @param entity
     *            data model object to extract the data from
     */
    public EntityTabContentType generateTabContent(Muid muid) {
        @SuppressWarnings("unchecked")
        EntityTabContentType tabContent =
                (EntityTabContentType) entityTabContentFactory
                        .createTabContent(getEntityTabType());
        return tabContent;
    }

    /**
     * fill tab preview with data
     * 
     * @param entity
     *            data model object to extract the data from
     */
    public EntityTabPreviewType generateTabPreview(Muid muid) {
        @SuppressWarnings("unchecked")
        EntityTabPreviewType tabPreview =
                (EntityTabPreviewType) entityTabPreviewFactory
                        .createTabPreview(getEntityTabType());
        return tabPreview;
    }

}
