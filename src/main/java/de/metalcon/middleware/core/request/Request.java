package de.metalcon.middleware.core.request;

public interface Request {

    String getMessage();

    void handleResponse(String response, AsyncRequestDispatcher dispatcher);

}
