package org.example.app.server.config;

public class ConfigForServer {
    public static final int PORT = 8189;
    public static final String HOST = "localhost";
    public final static String API_VERSION = "/api/v1";
    public static final String BASE_URL = "http://" + HOST + ":" + PORT;
    public static final String V1_API_URL = BASE_URL + API_VERSION;
}
