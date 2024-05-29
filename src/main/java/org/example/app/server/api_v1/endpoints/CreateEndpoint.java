package org.example.app.server.api_v1.endpoints;

import jakarta.ws.rs.core.Response;
import org.example.app.server.api_v1.controllers.CreateController;
import org.example.app.server.api_v1.db_connect.DbConnectInit;
import org.example.app.server.api_v1.entity.User;
import org.example.app.server.api_v1.utils.ActionAnswer;
import org.example.app.server.api_v1.utils.CreateUpdateParams;
import org.example.app.server.api_v1.utils.validate.validate_entity.ValidateAnswer;

import java.util.ArrayList;
import java.util.List;

import static org.example.app.server.api_v1.utils.ValidateUtils.validateProcessing;

public class CreateEndpoint {
    private final DbConnectInit connection;

    public CreateEndpoint(DbConnectInit connection) {
        this.connection = connection;
    }

    public ActionAnswer<User> createUserEndpoint(User user) {
            CreateUpdateParams createParams = new CreateUpdateParams();
            List<ValidateAnswer> validateAnswers = new ArrayList<>();
            String firstName = user.getFirstName();
            if (firstName == null) {
                ValidateAnswer firstNameValidate = new ValidateAnswer();
                firstNameValidate.addError("First name required");
                validateAnswers.add(firstNameValidate);
            } else {
                ValidateAnswer validateFirstName = createParams.setFirstName(firstName);
                validateAnswers.add(validateFirstName);
            }
            String lastName = user.getLastName();
            if (lastName == null) {
                ValidateAnswer lastNameValidate = new ValidateAnswer();
                lastNameValidate.addError("Last name required");
                validateAnswers.add(lastNameValidate);
            } else {
                ValidateAnswer validateLastName = createParams.setLastName(lastName);
                validateAnswers.add(validateLastName);
            }
            String email = user.getEmail();
            if (email == null) {
                ValidateAnswer emailValidate = new ValidateAnswer();
                emailValidate.addError("Email required");
                validateAnswers.add(emailValidate);
            } else {
                ValidateAnswer validateEmail = createParams.setEmail(email, connection, false);
                validateAnswers.add(validateEmail);
            }
            String phone = user.getPhone();
            if (phone == null) {
                ValidateAnswer phoneValidate = new ValidateAnswer();
                phoneValidate.addError("Phone required");
                validateAnswers.add(phoneValidate);
            } else {
                ValidateAnswer validatePhone = createParams.setPhone(phone);
                validateAnswers.add(validatePhone);
            }

            List<String> validateErrors = validateProcessing(validateAnswers);
            ActionAnswer<User> create = new ActionAnswer<>();
            if (!validateErrors.isEmpty()) {

                create.setMsg("Validation error");
                create.setErrors(validateErrors);
                create.setStatusCode(Response.Status.CONFLICT.getStatusCode());
                return create;
            }
            CreateController controllerCreateUser = new CreateController();
            create = controllerCreateUser.controllerCreateUser(connection, createParams);
            if (!create.isSuccess()) {
                create.setStatusCode(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
                return create;
            }
            create.setStatusCode(Response.Status.CREATED.getStatusCode());
            return create;
    }
}

