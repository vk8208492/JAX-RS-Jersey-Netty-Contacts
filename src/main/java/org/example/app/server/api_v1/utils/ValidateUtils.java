package org.example.app.server.api_v1.utils;

import org.example.app.server.api_v1.utils.validate.validate_entity.ValidateAnswer;

import java.util.List;

public class ValidateUtils {

    public static List<String> validateProcessing(List<ValidateAnswer> answers) {
        List<String> errors = new java.util.ArrayList<>();
        answers.forEach(
            answer -> {
                if (!answer.isValid()) {
                    errors.addAll(answer.getErrorsList());
                }
            }
        );
        return errors;
    }
}
