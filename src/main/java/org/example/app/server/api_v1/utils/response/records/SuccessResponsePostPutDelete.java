package org.example.app.server.api_v1.utils.response.records;

import org.example.app.server.api_v1.entity.BaseEntity;

public record SuccessResponsePostPutDelete <T extends BaseEntity>(
        String SuccessMsg,
        T data
) {
}
