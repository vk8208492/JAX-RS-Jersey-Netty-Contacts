package org.example.app.server.api_v1.endpoints;

import jakarta.ws.rs.core.Response;
import org.example.app.server.api_v1.controllers.ReadController;
import org.example.app.server.api_v1.controllers.UpdateController;
import org.example.app.server.api_v1.db_connect.DbConnectInit;
import org.example.app.server.api_v1.entity.User;
import org.example.app.server.api_v1.utils.ActionAnswer;
import org.example.app.server.api_v1.utils.CreateUpdateParams;
import org.example.app.server.api_v1.utils.ReadParams;
import org.example.app.server.api_v1.utils.validate.validate_entity.ValidateAnswer;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.example.app.server.api_v1.utils.ValidateUtils.validateProcessing;

public class UpdateEndpoint {
    private final DbConnectInit connection;

    public UpdateEndpoint(DbConnectInit connection) {
        this.connection = connection;
    }

    public ActionAnswer<User> updateUserEndpoint(User userUpdateData) {
        CreateUpdateParams updateParams = new CreateUpdateParams();
        ReadParams readParamsById = new ReadParams();
        List<ValidateAnswer> validateIdList = new ArrayList<>();
        ActionAnswer<User> update = new ActionAnswer<>();
        ValidateAnswer validateId = readParamsById.setId(userUpdateData.getId().toString());
        validateIdList.add(validateId);
        List<String> validateIdErrors = validateProcessing(validateIdList);
        if (!validateIdErrors.isEmpty()) {
            update.setMsg("Validation error");
            update.setErrors(validateIdErrors);
            update.setStatusCode(Response.Status.CONFLICT.getStatusCode());
            return update;
        }
        ReadController readController = new ReadController();
        ActionAnswer<User> isIdExists = readController.controllerReadById(connection, readParamsById);

        if (!isIdExists.getEntity().isEmpty()) {
            User user = isIdExists.getEntity().getFirst();
            List<ValidateAnswer> validateAnswers = new ArrayList<>();
            Optional<String> firstName = Optional.ofNullable(userUpdateData.getFirstName());
            if (firstName.isEmpty()) {
                updateParams.setFirstName(user.getFirstName());
            } else {
                ValidateAnswer validateFirstName = updateParams.setFirstName(firstName.get());
                validateAnswers.add(validateFirstName);
            }

            Optional<String> lastName = Optional.ofNullable(userUpdateData.getLastName());
            if (lastName.isEmpty()) {
                updateParams.setLastName(user.getLastName());
            } else {
                ValidateAnswer validateLastName = updateParams.setLastName(lastName.get());
                validateAnswers.add(validateLastName);
            }

            Optional<String> email = Optional.ofNullable(userUpdateData.getEmail());
            if (email.isEmpty()) {
                updateParams.setEmail(user.getEmail(), connection, true);
            } else {
                ValidateAnswer validateEmail = updateParams.setEmail(email.get(), connection, false);
                validateAnswers.add(validateEmail);
            }

            Optional<String> phone = Optional.ofNullable(userUpdateData.getPhone());
            if (phone.isEmpty()) {
                updateParams.setPhone(user.getPhone());
            } else {
                ValidateAnswer validatePhone = updateParams.setPhone(phone.get());
                validateAnswers.add(validatePhone);
            }

            List<String> validateUpdateErrors = validateProcessing(validateAnswers);
            if (!validateUpdateErrors.isEmpty()) {
                update.setMsg("Validation error");
                update.setErrors(validateIdErrors);
                update.setStatusCode(Response.Status.CONFLICT.getStatusCode());
            }

            UpdateController updateController = new UpdateController();
            update = updateController.updateUser(connection, user, updateParams);
            if (!update.isSuccess()) {
                update.setStatusCode(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
                return update;
            }
            update.setStatusCode(Response.Status.ACCEPTED.getStatusCode());
            return update;
        } else {
            update.setMsg("User not found");
            update.addError("User not found");
            update.setStatusCode(Response.Status.NOT_FOUND.getStatusCode());
            return update;
        }
    }
}
