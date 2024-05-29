package org.example.app.server.api_v1.utils.validate.enums;

import lombok.Getter;

@Getter
public enum EValidateQuery implements IValidateUnit {
    OFFSET("offset must be integer number", "^[0-9]+$"),
    LIMIT("limit must be integer number", "^[0-9]+$"),
    EXCLUDE_COLUMNS("excludeColumns must be string separated by comma or a single word", "^[A-Za-z0-9,_]*[A-Za-z0-9_]+$"),
    ID("id must be integer number", "^[0-9]+$");

    private final String errorMsg;
    private final String validationRegex;

    EValidateQuery(String errorMsg, String validationRegex) {
        this.errorMsg = errorMsg;
        this.validationRegex = validationRegex;
    }

}
