package de.metalcon.middleware.controller.entity.impl.band;

import org.springframework.stereotype.Component;

import de.metalcon.middleware.controller.entity.generator.AboutTabGenerator;
import de.metalcon.middleware.domain.entity.Entity;
import de.metalcon.middleware.view.entity.tab.content.EntityTabContent;
import de.metalcon.middleware.view.entity.tab.preview.EntityTabPreview;

/**
 * about tab generator for bands
 */
@Component
public class BandAboutTabGenerator extends AboutTabGenerator {

    /**
     * fill about tab content with band data
     * 
     * @param tabContent
     *            empty about tab content
     * @param entity
     *            band object
     */
    @Override
    public void generateTabContent(EntityTabContent tabContent, Entity entity) {
        // TODO Auto-generated method stub
    }

    /**
     * fill about tab preview with band data
     * 
     * @param tabPreview
     *            empty about tab preview
     * @param entity
     *            band object
     */
    @Override
    public void generateTabPreview(EntityTabPreview tabPreview, Entity entity) {
        // TODO Auto-generated method stub
    }

}
