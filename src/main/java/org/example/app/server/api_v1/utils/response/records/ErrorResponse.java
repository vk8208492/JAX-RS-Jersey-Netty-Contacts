package org.example.app.server.api_v1.utils.response.records;

import java.util.List;

public record ErrorResponse (
        String error,
        List<String> errorMessages
) {

}
