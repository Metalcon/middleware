package de.metalcon.middleware.controller.entity.generator.impl;

import org.springframework.stereotype.Component;

import de.metalcon.middleware.controller.entity.generator.EntityTabGenerator;
import de.metalcon.middleware.view.entity.tab.EntityTabType;
import de.metalcon.middleware.view.entity.tab.content.impl.PhotosTabContent;
import de.metalcon.middleware.view.entity.tab.preview.impl.PhotosTabPreview;

@Component
public abstract class PhotosTabGenerator extends
        EntityTabGenerator<PhotosTabContent, PhotosTabPreview> {

    public PhotosTabGenerator() {
        super(EntityTabType.PHOTOS);
    }

}
