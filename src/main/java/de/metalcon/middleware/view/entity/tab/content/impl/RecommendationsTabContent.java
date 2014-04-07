package de.metalcon.middleware.view.entity.tab.content.impl;

import java.util.ArrayList;
import java.util.List;

import de.metalcon.middleware.view.entity.tab.EntityTabType;
import de.metalcon.middleware.view.entity.tab.content.EntityTabContent;

public class RecommendationsTabContent extends EntityTabContent {

    private List<String[]> recommendedBands = null;

    private List<String> myRecords = null;

    public RecommendationsTabContent() {
        super(EntityTabType.RECOMMENDATIONS);
    }

    public List<String[]> getRecommendedBands() {
        return recommendedBands;
    }

    public void setRecommendedBands(List<String[]> recommendedBands) {
        this.recommendedBands = recommendedBands;
    }

    public void addRecommendedBand(String[] recommendedBand) {
        if (recommendedBands == null) {
            recommendedBands = new ArrayList<String[]>();
        }
        recommendedBands.add(recommendedBand);
    }

    public List<String> getMyRecords() {
        return myRecords;
    }

    public void setMyRecords(List<String> myRecords) {
        this.myRecords = myRecords;
    }

    public void addRecord(String record) {
        if (myRecords == null) {
            myRecords = new ArrayList<String>();
        }
        myRecords.add(record);
    }

}
