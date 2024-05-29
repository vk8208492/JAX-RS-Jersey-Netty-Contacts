package org.example.app.server.api_v1.endpoints;


import jakarta.ws.rs.core.Response;
import org.example.app.server.api_v1.controllers.DeleteController;
import org.example.app.server.api_v1.controllers.ReadController;
import org.example.app.server.api_v1.db_connect.DbConnectInit;
import org.example.app.server.api_v1.entity.User;
import org.example.app.server.api_v1.utils.ActionAnswer;
import org.example.app.server.api_v1.utils.ReadParams;
import org.example.app.server.api_v1.utils.validate.validate_entity.ValidateAnswer;

import java.util.List;

import static org.example.app.server.api_v1.utils.ValidateUtils.validateProcessing;

public class DeleteEndpoint {
    private final DbConnectInit connection;

    public DeleteEndpoint(DbConnectInit connection) {
        this.connection = connection;
    }

    public ActionAnswer<User> deleteUserEndpoint(String idData) {
        ReadParams readParamsById = new ReadParams();
        ValidateAnswer validateId = readParamsById.setId(idData);
        ActionAnswer<User> delete = new ActionAnswer<>();
        List<ValidateAnswer> validateAnswers = List.of(validateId);
        List<String> errorsValidateDelete = validateProcessing(validateAnswers);
        if (!errorsValidateDelete.isEmpty()) {
            delete.setMsg("Validation error");
            delete.setErrors(errorsValidateDelete);
            delete.setStatusCode(Response.Status.CONFLICT.getStatusCode());
            return delete;
        }
        ReadController readController = new ReadController();
        ActionAnswer<User> checkUserExists = readController.controllerReadById(connection, readParamsById);
        if (!checkUserExists.isSuccess()) {
            if (checkUserExists.getErrors().isEmpty()) {
                delete = checkUserExists;
                delete.setErrors(List.of("User not found"));
                delete.setStatusCode(Response.Status.NOT_FOUND.getStatusCode());
                return delete;
            }
            delete.setErrors(checkUserExists.getErrors());
            delete.setMsg("Internal server error");
            delete.setStatusCode(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
            return delete;
        }
        if (!checkUserExists.getEntity().isEmpty()) {
            User user = checkUserExists.getEntity().getFirst();
            DeleteController deleteController = new DeleteController();
            delete = deleteController.deleteUser(connection, user);
            delete.setStatusCode(Response.Status.NO_CONTENT.getStatusCode());
            return delete;
        } else {
            delete.setMsg("User not found");
            delete.setStatusCode(Response.Status.NOT_FOUND.getStatusCode());
            return delete;
        }
    }
}
