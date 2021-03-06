package de.metalcon.middleware.view.entity;

import java.util.HashMap;
import java.util.Map;

import de.metalcon.middleware.domain.entity.EntityData;
import de.metalcon.middleware.domain.entity.EntityType;
import de.metalcon.middleware.view.BaseView;
import de.metalcon.middleware.view.entity.tab.EntityTabType;
import de.metalcon.middleware.view.entity.tab.content.EntityTabContent;
import de.metalcon.middleware.view.entity.tab.preview.EntityTabPreview;

/**
 * basic entity view holding tab content and previews
 */
public abstract class EntityView extends BaseView {

    private final EntityType entityType;

    private EntityData entityData;

    /**
     * tab content (inner content)
     */
    private EntityTabContent entityTabContent;

    /**
     * tab previews on entity page
     */
    private Map<EntityTabType, EntityTabPreview> entityTabPreviews;

    public EntityView(
            EntityType entityType) {
        super("entity");
        this.entityType = entityType;
        entityTabContent = null;
        entityTabPreviews = null;
    }

    /**
     * @return type of the entity displayed
     */
    public EntityType getEntityType() {
        return entityType;
    }

    /**
     * @return tab content (inner content)
     */
    public final EntityTabContent getEntityTabContent() {
        return entityTabContent;
    }

    public final void setEntityTabContent(EntityTabContent entityTab) {
        entityTabContent = entityTab;
    }

    /**
     * @return tab previews on entity page
     */
    public final Map<String, EntityTabPreview> getEntityTabPreviews() {
        if (entityTabPreviews == null) {
            return null;
        }
        Map<String, EntityTabPreview> m =
                new HashMap<String, EntityTabPreview>();
        for (Map.Entry<EntityTabType, EntityTabPreview> entityTabPreview : entityTabPreviews
                .entrySet()) {
            m.put(entityTabPreview.getKey().toString(),
                    entityTabPreview.getValue());
        }
        return m;
    }

    public final void setEntityTabPreviews(
            Map<EntityTabType, EntityTabPreview> entityTabPreviews) {
        this.entityTabPreviews = entityTabPreviews;
    }

    public EntityData getEntityData() {
        return entityData;
    }

    public void setEntityData(final EntityData entityData) {
        this.entityData = entityData;
    }

}
