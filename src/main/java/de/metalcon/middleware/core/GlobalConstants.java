package de.metalcon.middleware.core;

/**
 * Class which stores global constants. all fields in this class should be
 * public static final
 */
public class GlobalConstants {

    /**
     * Can't set the cookie name with this one yet.
     */
    public static final String SESSION_COOKIE_NAME = "JSESSIONID";

    public static final String REMEMBERME_COOKIE_NAME = "REMEMBERME";

    public static final int REMEMBERME_VALIDITY_SECONDS = 60 * 60 * 24 * 30 * 6; // 6 Months

    public static final String REMEMBERME_KEY = "MetalconRememeberMe";

    public static final String REMEMBERME_PARAMETER = "rememberme";

    public static final String RESOURCE_PATH = "/resources/";

    public static final String RESOURCE_PATH_ANT = "/resources/**";
}
