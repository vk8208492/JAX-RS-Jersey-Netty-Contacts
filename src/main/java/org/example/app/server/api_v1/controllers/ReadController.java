package org.example.app.server.api_v1.controllers;

import org.example.app.server.api_v1.db_connect.DbConnectInit;
import org.example.app.server.api_v1.entity.User;
import org.example.app.server.api_v1.model.GetUsersModel;
import org.example.app.server.api_v1.utils.ActionAnswer;
import org.example.app.server.api_v1.utils.ReadParams;

public class ReadController {
    public ActionAnswer<User> controllerReadAll(DbConnectInit connect, ReadParams readParams) {
        GetUsersModel getUsersModel = new GetUsersModel(readParams);
        return getUsersModel.getUsers(connect);
    }

    public ActionAnswer<User> controllerReadById(DbConnectInit connect, ReadParams readParams) {
        GetUsersModel getUsersModel = new GetUsersModel(readParams);
        return getUsersModel.getUser(connect);
    }
}
