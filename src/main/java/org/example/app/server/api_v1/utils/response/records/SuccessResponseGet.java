package org.example.app.server.api_v1.utils.response.records;

import org.example.app.server.api_v1.entity.BaseEntity;

import java.util.List;

public record SuccessResponseGet<T extends BaseEntity>(
    String successMsg,
    List<T> data
) {

}
