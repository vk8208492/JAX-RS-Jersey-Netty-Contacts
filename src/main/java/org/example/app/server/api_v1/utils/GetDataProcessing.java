package org.example.app.server.api_v1.utils;

import org.example.app.server.api_v1.controllers.ReadController;
import org.example.app.server.api_v1.db_connect.DbConnectInit;
import org.example.app.server.api_v1.entity.User;

public class GetDataProcessing {

    public ActionAnswer<User> getProcessedData(DbConnectInit connection,
                                               ReadParams readParams,
                                               ReadController readController,
                                               boolean scalar) {
            ActionAnswer<User> get;
            if (scalar) {
                get = readController.controllerReadById(connection, readParams);
            } else {
                get = readController.controllerReadAll(connection, readParams);
            }
            return get;
    }
}
