package org.example.app.server.api_v1.utils.response;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.HttpMethod;
import org.example.app.server.api_v1.entity.BaseEntity;
import org.example.app.server.api_v1.utils.ActionAnswer;
import org.example.app.server.api_v1.utils.response.records.ErrorResponse;
import org.example.app.server.api_v1.utils.response.records.SuccessResponseGet;
import org.example.app.server.api_v1.utils.response.records.SuccessResponsePostPutDelete;

import java.util.List;

import static org.example.app.server.api_v1.utils.json.JsonWork.toJsonString;

public class ResponseCreator {
    private static final List<String> NOT_SAVE_METHODS = List.of(HttpMethod.POST, HttpMethod.PUT, HttpMethod.DELETE);

    public static <T extends BaseEntity> Response buildResponse (ActionAnswer<T> actionAnswer, String method) {
        try{
            if (actionAnswer.isSuccess() && method.equals(HttpMethod.GET)) {
                SuccessResponseGet<T> successResponseGet = new SuccessResponseGet<>(
                        actionAnswer.getMsg(),
                        actionAnswer.getEntity()
                );
                String jsonGet = toJsonString(successResponseGet);
                return Response.ok(jsonGet).status(actionAnswer.getStatusCode()).type(MediaType.APPLICATION_JSON).build();
            } else if (actionAnswer.isSuccess() && NOT_SAVE_METHODS.contains(method)) {
                SuccessResponsePostPutDelete<T> successResponsePostPutDelete = new SuccessResponsePostPutDelete<>(
                        actionAnswer.getMsg(),
                        actionAnswer.getEntity().getFirst()
                );
                String jsonPostPutDelete = toJsonString(successResponsePostPutDelete);
                return Response.ok(jsonPostPutDelete).status(actionAnswer.getStatusCode()).type(MediaType.APPLICATION_JSON).build();
            } else {
                ErrorResponse errorResponse = new ErrorResponse(
                        actionAnswer.getMsg(),
                        actionAnswer.getErrors()
                );
                String jsonError = toJsonString(errorResponse);
                return Response.status(actionAnswer.getStatusCode()).entity(jsonError).type(MediaType.APPLICATION_JSON).build();
            }
        } catch (JsonProcessingException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Internal server error").type(MediaType.TEXT_PLAIN).build();
        }

    }
}
