package org.example.app.server.api_v1.endpoints;

import jakarta.ws.rs.core.Response;
import org.example.app.server.api_v1.controllers.ReadController;
import org.example.app.server.api_v1.db_connect.DbConnectInit;
import org.example.app.server.api_v1.entity.User;
import org.example.app.server.api_v1.utils.ActionAnswer;
import org.example.app.server.api_v1.utils.GetDataProcessing;
import org.example.app.server.api_v1.utils.ReadParams;


public class GetAllEndpoint {
    private final DbConnectInit connection;

    public GetAllEndpoint(DbConnectInit connection) {
        this.connection = connection;
    }

    public ActionAnswer<User> getUsersEndpoint() {
        ReadParams readParams = new ReadParams();
        ReadController readControllerWithout = new ReadController();
        GetDataProcessing getDataProcessing = new GetDataProcessing();
        ActionAnswer<User> get = getDataProcessing.getProcessedData(connection, readParams, readControllerWithout, false);
        if (get.isSuccess()) {
            get.setStatusCode(Response.Status.OK.getStatusCode());
            return get;
        }
        get.setStatusCode(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
        return get;
    }


}
