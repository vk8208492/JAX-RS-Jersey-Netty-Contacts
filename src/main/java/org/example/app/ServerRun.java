package org.example.app;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.app.server.api_v1.router.ApiV1Router;
import org.example.app.server.provider.InitServer;

import java.net.MalformedURLException;

public class ServerRun {
    private static final Logger START_SERVER_LOGGER =
            LogManager.getLogger(ServerRun.class);
    private static final Logger CONSOLE_START_SERVER_LOGGER =
            LogManager.getLogger("console_logger");
    public void run() throws MalformedURLException {
        String init = InitServer.startHttpServer(ApiV1Router.class);
        START_SERVER_LOGGER.info(init);
        CONSOLE_START_SERVER_LOGGER.info(init);
    }

    public static void main(String[] args) {
        try {
            new ServerRun().run();
        } catch (MalformedURLException e) {
            START_SERVER_LOGGER.error(String.format("Server start error - %s", e));
            CONSOLE_START_SERVER_LOGGER.error("Server start error");
        }
    }
}