package org.example.app.server.provider.middleware;

import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.container.ContainerResponseContext;
import jakarta.ws.rs.container.ContainerResponseFilter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggingMiddleware implements ContainerRequestFilter, ContainerResponseFilter {
    private static final Logger REQUEST_RESPONSE_LOGGER =
            LogManager.getLogger(LoggingMiddleware.class);
    private static final Logger CONSOLE_REQUEST_RESPONSE_LOGGER =
            LogManager.getLogger("console_logger");
    @Override
    public void filter(ContainerRequestContext requestContext) {
        REQUEST_RESPONSE_LOGGER.info(
                String.format("Request intercepted for path: %s", requestContext.getHeaders())
        );
        CONSOLE_REQUEST_RESPONSE_LOGGER.info(
                String.format("Request intercepted for path: %s", requestContext.getHeaders())
        );
    }

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) {
        REQUEST_RESPONSE_LOGGER.info(
                String.format("Response intercepted for path: %s", requestContext.getHeaders())
        );
        CONSOLE_REQUEST_RESPONSE_LOGGER.info(
                String.format("Response intercepted for path: %s", requestContext.getHeaders())
        );
    }
}
