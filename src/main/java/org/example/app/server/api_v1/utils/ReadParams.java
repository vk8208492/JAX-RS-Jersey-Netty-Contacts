package org.example.app.server.api_v1.utils;
import lombok.Getter;
import org.example.app.server.api_v1.utils.validate.Validator;
import org.example.app.server.api_v1.utils.validate.enums.EValidateQuery;
import org.example.app.server.api_v1.utils.validate.validate_entity.ValidateAnswer;

import java.util.ArrayList;
import java.util.List;

public class ReadParams {
    @Getter
    private final int limit;
    @Getter
    private final int offset;
    @Getter
    private final List<String> excludeColumns;
    private final Validator<EValidateQuery> validator = new Validator<>();
    @Getter
    private Long id;


    public ValidateAnswer setId(String id) {
        ValidateAnswer answer = validator.validate(id, EValidateQuery.ID);
        if (answer.isValid()) {
            this.id = Long.parseLong(id);
            return answer;
        }
        return answer;
    }


    public ReadParams() {
        this.limit = 0;
        this.offset = 0;
        this.excludeColumns = new ArrayList<>();
    }

}
