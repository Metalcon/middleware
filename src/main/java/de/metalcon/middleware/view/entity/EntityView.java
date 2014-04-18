package de.metalcon.middleware.view.entity;

import java.util.HashMap;
import java.util.Map;

import de.metalcon.domain.Muid;
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

    /**
     * identifier of the entity displayed
     */
    private Muid muid;

    private String urlPath;

    private String entityName;

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
        muid = null;
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
     * @return entity identifier
     */
    public final String getMuidSerialized() {
        return muid.toString();
    }

    /**
     * @return entity identifier
     */
    public final Muid getMuid() {
        return muid;
    }

    public final void setMuid(Muid muid) {
        this.muid = muid;
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

    public String getUrlPath() {
        return urlPath;
    }

    public void setUrlPath(String urlPath) {
        this.urlPath = urlPath;
    }

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

}
