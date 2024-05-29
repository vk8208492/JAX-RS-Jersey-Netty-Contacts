package org.example.app.server.api_v1.router;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.example.app.server.api_v1.controllers.DBController;
import org.example.app.server.api_v1.db_connect.DbConnectInit;
import org.example.app.server.api_v1.endpoints.*;
import org.example.app.server.api_v1.entity.User;
import org.example.app.server.api_v1.utils.ActionAnswer;
import org.example.app.server.config.ConfigForServer;
import org.example.app.server.api_v1.utils.response.ResponseCreator;

@Path(ConfigForServer.API_VERSION + "/users")
public class ApiV1Router {
    private final DbConnectInit connection;

    public ApiV1Router() {

        DBController dbController = new DBController();
        this.connection = dbController.connect();
    }

    @GET
    @Path("/all")
    public Response fetchAllUsers() {
        GetAllEndpoint getAllValidate = new GetAllEndpoint(this.connection);
        ActionAnswer<User> all = getAllValidate.getUsersEndpoint();
        return ResponseCreator.buildResponse(
                all,
                HttpMethod.GET
        );
    }

    @GET
    @Path("/one")
    public Response fetchOneUser(@QueryParam("id") String id) {
        GetByIdEndpoint getByIdValidate = new GetByIdEndpoint(this.connection);
        ActionAnswer<User> one = getByIdValidate.getUserByIdEndpoint(id);
        return ResponseCreator.buildResponse(
                one,
                HttpMethod.GET
        );
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("/create")
    public Response createUser(User user) {
        CreateEndpoint createEndpoint = new CreateEndpoint(this.connection);
        ActionAnswer<User> create = createEndpoint.createUserEndpoint(user);
        return ResponseCreator.buildResponse(
                create,
                HttpMethod.POST
        );
    }

    @PUT
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("/update")
    public Response updateUser(User user){
        UpdateEndpoint updateEndpoint = new UpdateEndpoint(this.connection);
        ActionAnswer<User> update = updateEndpoint.updateUserEndpoint(user);
        return ResponseCreator.buildResponse(
                update,
                HttpMethod.PUT
        );
    }

    @DELETE
    @Path("/delete")
    public Response deleteUser(@QueryParam("id") String id) {
        DeleteEndpoint deleteEndpoint = new DeleteEndpoint(this.connection);
        ActionAnswer<User> delete = deleteEndpoint.deleteUserEndpoint(id);
        return ResponseCreator.buildResponse(
                delete,
                HttpMethod.DELETE
        );
    }
}
