package de.metalcon.middleware.controller.entity.impl.genre;

import java.util.List;

import org.springframework.stereotype.Component;

import de.metalcon.middleware.controller.entity.generator.impl.RecordsTabGenerator;
import de.metalcon.middleware.sdd.SddOutput;
import de.metalcon.middleware.sdd.record.RecordEntry;
import de.metalcon.middleware.view.entity.tab.content.impl.RecordsTabEntry;

@Component
public class GenreRecordsTabGenerator extends RecordsTabGenerator {

    @Override
    protected List<RecordsTabEntry> getRecordsContent(SddOutput page) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    protected List<RecordEntry> getRecordsPreview(SddOutput page) {
        // TODO Auto-generated method stub
        return null;
    }

}
