package org.example.app.server.api_v1.endpoints;

import jakarta.ws.rs.core.Response;

import org.example.app.server.api_v1.controllers.ReadController;
import org.example.app.server.api_v1.db_connect.DbConnectInit;
import org.example.app.server.api_v1.entity.User;
import org.example.app.server.api_v1.utils.ActionAnswer;
import org.example.app.server.api_v1.utils.GetDataProcessing;
import org.example.app.server.api_v1.utils.ReadParams;
import org.example.app.server.api_v1.utils.validate.validate_entity.ValidateAnswer;

import java.util.List;

import static org.example.app.server.api_v1.utils.ValidateUtils.validateProcessing;

public class GetByIdEndpoint {
    private final DbConnectInit connection;

    public GetByIdEndpoint(DbConnectInit connection) {
        this.connection = connection;
    }

    public ActionAnswer<User> getUserByIdEndpoint(String idData) {
        ReadParams readParamsById = new ReadParams();
        ValidateAnswer validateId = readParamsById.setId(idData);
        List<ValidateAnswer> validateAnswers = List.of(validateId);
        ActionAnswer<User> getById = new ActionAnswer<>();
        List<String> getByIdValidateError = validateProcessing(validateAnswers);
        if (!getByIdValidateError.isEmpty()) {
            getById.setMsg("Validation error");
            getById.setErrors(getByIdValidateError);
            getById.setStatusCode(Response.Status.CONFLICT.getStatusCode());
            return getById;
        }
        ReadController readControllerById = new ReadController();
        GetDataProcessing getDataProcessing = new GetDataProcessing();
        getById = getDataProcessing.getProcessedData(connection, readParamsById, readControllerById, true);
        if (!getById.isSuccess()) {
            if (getById.getErrors().isEmpty()) {
                getById.setErrors(List.of("User not found"));
                getById.setStatusCode(Response.Status.NOT_FOUND.getStatusCode());
                return getById;
            }
            getById.setStatusCode(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
            return getById;
        }
        getById.setStatusCode(Response.Status.OK.getStatusCode());
        return getById;
    }

}
