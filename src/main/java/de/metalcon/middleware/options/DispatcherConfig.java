package de.metalcon.middleware.options;

import java.lang.invoke.MethodHandles;

public class DispatcherConfig extends de.metalcon.dbhelper.Options {

    static {
        try {
            DispatcherConfig.initialize(
                    "/usr/share/metalcon/middleware/main.cfg", MethodHandles
                            .lookup().lookupClass());
        } catch (IllegalArgumentException | IllegalAccessException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public static String SDD_ENDPOINT;

    public static String MUSIC_STREAMING_SERVER_ENDPOINT;

    public static String URL_MAPPING_SERVER_ENDPOINT;

    public static String LIKE_SERVER_ENDPOINT;

    public static String RECOMMENDATION_SERVER_ENDPOINT;
}
