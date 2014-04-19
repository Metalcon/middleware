package de.metalcon.middleware.domain.entity;

import net.hh.request_dispatcher.Dispatcher;
import de.metalcon.domain.Muid;

public class InstrumentData extends EntityData {

    public InstrumentData(
            final Dispatcher dispatcher,
            final Muid userID,
            final Muid entityID) {
        super(dispatcher, userID, entityID);
    }
}
