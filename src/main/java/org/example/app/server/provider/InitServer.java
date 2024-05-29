package org.example.app.server.provider;

import jakarta.ws.rs.core.UriBuilder;
import org.example.app.server.config.ConfigForServer;
import org.example.app.server.provider.middleware.LoggingMiddleware;
import org.glassfish.jersey.netty.httpserver.NettyHttpContainerProvider;
import org.glassfish.jersey.server.ResourceConfig;

import java.net.MalformedURLException;
import java.net.URI;

public class InitServer {

    public static String startHttpServer(final Class<?>... classes)
            throws MalformedURLException {
        final ResourceConfig rc = new ResourceConfig(classes).register(LoggingMiddleware.class);
        URI baseUri = UriBuilder.fromUri(ConfigForServer.V1_API_URL)
                .port(ConfigForServer.PORT).build();
        NettyHttpContainerProvider.createServer(baseUri, rc, false);
        return String.format("App running on %s%n",
                baseUri.toURL().toExternalForm());
    }
}
