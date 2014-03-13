package de.metalcon.middleware.core.request.newsfeed;

import de.metalcon.middleware.core.request.AbstractRequest;
import de.metalcon.middleware.core.request.RequestData;
import de.metalcon.middleware.core.request.ResponseData;

public abstract class AbstractNewsfeedRequest extends AbstractRequest {

    @Override
    protected String buildMessage(RequestData requestData) {
        NewsfeedRequestData data = (NewsfeedRequestData) requestData;
        // TODO: build message
        return null;
    }

    @Override
    protected ResponseData parseResponse(String response) {
        NewsfeedResponseData data = new NewsfeedResponseData();
        // TODO: parse Response
        return data;
    }

}
