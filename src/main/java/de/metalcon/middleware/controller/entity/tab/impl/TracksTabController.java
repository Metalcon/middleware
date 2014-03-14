package de.metalcon.middleware.controller.entity.tab.impl;

import org.springframework.stereotype.Component;

import de.metalcon.middleware.controller.entity.tab.EntityTabController;
import de.metalcon.middleware.view.entity.tab.EntityTabType;

@Component
public class TracksTabController extends EntityTabController {

    public TracksTabController() {
        super(EntityTabType.TRACKS);
    }

}
