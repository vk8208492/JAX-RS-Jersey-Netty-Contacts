package org.example.app.server.api_v1.utils;

import lombok.Getter;
import org.example.app.server.api_v1.db_connect.DbConnectInit;
import org.example.app.server.api_v1.repository.UserRepository;
import org.example.app.server.api_v1.utils.validate.Validator;
import org.example.app.server.api_v1.utils.validate.enums.EValidateUser;
import org.example.app.server.api_v1.utils.validate.validate_entity.ValidateAnswer;

@Getter
public class CreateUpdateParams {
    private String firstName;
    private String lastName;
    private String email;
    private String phone;

    public CreateUpdateParams() {
    }

    public ValidateAnswer setFirstName(String firstName) {
        Validator<EValidateUser> validator = new Validator<>();
        ValidateAnswer answer = validator.validate(firstName, EValidateUser.FIRST_NAME);
        if (answer.isValid()) {
            this.firstName = firstName;
            return answer;
        }
        return answer;
    }

    public ValidateAnswer setLastName(String lastName) {
        Validator<EValidateUser> validator = new Validator<>();
        ValidateAnswer answer = validator.validate(lastName, EValidateUser.LAST_NAME);
        if (answer.isValid()) {
            this.lastName = lastName;
            return answer;
        }
        return answer;
    }

    public ValidateAnswer setEmail(String email, DbConnectInit connection, boolean isSelfEmail) {
        Validator<EValidateUser> validator = new Validator<>();
        ValidateAnswer answer = validator.validate(email, EValidateUser.EMAIL);
        if (answer.isValid()) {
            if (!isSelfEmail) {
                if (isEmailExists(connection, email)) {
                    ValidateAnswer answerCheckEmail = new ValidateAnswer();
                    answerCheckEmail.addError("Email already exists");
                    return answerCheckEmail;
                }
            }
            this.email = email;
            return answer;
        }
        return answer;
    }

    private boolean isEmailExists(DbConnectInit connection, String email) {
        UserRepository userRepository = new UserRepository(connection);
        return userRepository.checkEmailExists(email);
    }

    public ValidateAnswer setPhone(String phone) {
        Validator<EValidateUser> validator = new Validator<>();
        ValidateAnswer answer = validator.validate(phone, EValidateUser.PHONE);
        if (answer.isValid()) {
            this.phone = phone;
            return answer;
        }
        return answer;
    }
}
