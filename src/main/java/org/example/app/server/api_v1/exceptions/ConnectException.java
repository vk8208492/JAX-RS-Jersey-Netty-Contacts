package org.example.app.server.api_v1.exceptions;

public class ConnectException extends RuntimeException {
    public ConnectException(String msg) {
            super(msg);
        }
}
