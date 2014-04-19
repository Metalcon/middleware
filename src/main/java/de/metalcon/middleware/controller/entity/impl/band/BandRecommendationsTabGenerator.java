package de.metalcon.middleware.controller.entity.impl.band;

import java.util.ArrayList;
import java.util.List;

import net.hh.request_dispatcher.Callback;

import org.springframework.stereotype.Component;

import de.metalcon.middleware.controller.entity.EntityController.Data;
import de.metalcon.middleware.controller.entity.generator.impl.RecommendationsTabGenerator;
import de.metalcon.middleware.domain.entity.EntityData;
import de.metalcon.middleware.view.entity.tab.content.impl.RecommendationsTabContent;
import de.metalcon.recommendation.api.requests.GetRecommendationRequest;
import de.metalcon.recommendation.api.responses.GetRecommendationResponse;

@Component
public class BandRecommendationsTabGenerator extends
        RecommendationsTabGenerator {

    @Override
    public RecommendationsTabContent generateTabContent(final Data data) {
        // TODO Auto-generated method stub
        final RecommendationsTabContent tabContent =
                super.generateTabContent(data);
        GetRecommendationRequest recommendationsRequest =
                new GetRecommendationRequest(data.getMuid(), null, null, 100);
        data.getDispatcher().execute(recommendationsRequest,
                new Callback<GetRecommendationResponse>() {

                    @Override
                    public void onSuccess(GetRecommendationResponse reply) {
                        reply.getRecommendations();
                        if (reply.getRecommendations() != null) {
                            List<EntityData> recEntities =
                                    new ArrayList<EntityData>(reply
                                            .getRecommendations().size());
                            for (int i = 0; i < reply.getRecommendations()
                                    .size(); i++) {
                                EntityData element =
                                        new EntityData(
                                                data.getDispatcher(),
                                                data.getUserSession().getMuid(),
                                                reply.getRecommendations().get(
                                                        i));
                                recEntities.add(element);
                            }
                            tabContent.setRecommendedEntities(recEntities);
                        }
                    }
                });
        return tabContent;
    }
}
