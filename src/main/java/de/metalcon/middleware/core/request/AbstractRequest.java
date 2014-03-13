package de.metalcon.middleware.core.request;

public abstract class AbstractRequest implements Request {

    private RequestData requestData;

    public RequestData getRequestData() {
        return requestData;
    }

    public void setRequestData(RequestData requestData) {
        this.requestData = requestData;
    }

    @Override
    public String getMessage() {
        return buildMessage(getRequestData());
    }

    @Override
    public void handleResponse(
            String response,
            AsyncRequestDispatcher dispatcher) {
        ResponseData responseData = parseResponse(response);
        onSuccess(responseData, dispatcher);
    }

    protected abstract String buildMessage(RequestData requestData);

    protected abstract ResponseData parseResponse(String response);

    protected abstract void onSuccess(
            ResponseData data,
            AsyncRequestDispatcher dispatcher);

}
