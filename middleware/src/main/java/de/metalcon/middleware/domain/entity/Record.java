package de.metalcon.middleware.domain.entity;

import de.metalcon.middleware.domain.Muid;

public class Record extends Entity {

    @Override
    public EntityType getEntityType() {
        return EntityType.RECORD;
    }
    
    private Muid band;
    
    private Integer releaseYear;

    public Record(String name) {
        super(name);
        band        = null;
        releaseYear = null;
    }
    
    public Muid getBand() {
        return band;
    }
    
    public void setBand(Muid band) {
        this.band = band;
    }
    
    public Integer getReleaseYear() {
        return releaseYear;
    }
    
    public void setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
    }
    
}