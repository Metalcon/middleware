package de.metalcon.middleware.controller.entity.impl.genre;

import java.util.List;

import org.springframework.stereotype.Component;

import de.metalcon.middleware.controller.entity.EntityController;
import de.metalcon.middleware.controller.entity.generator.impl.RecordsTabGenerator;
import de.metalcon.middleware.domain.entity.RecordData;
import de.metalcon.middleware.sdd.record.RecordEntry;

@Component
public class GenreRecordsTabGenerator extends RecordsTabGenerator {

    @Override
    protected List<RecordData> getRecordsContent(
            final EntityController.Data data) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    protected List<RecordEntry> getRecordsPreview(
            final EntityController.Data data) {
        // TODO Auto-generated method stub
        return null;
    }

}
