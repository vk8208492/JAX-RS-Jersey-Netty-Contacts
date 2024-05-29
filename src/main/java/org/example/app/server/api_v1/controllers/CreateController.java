package org.example.app.server.api_v1.controllers;


import org.example.app.server.api_v1.db_connect.DbConnectInit;
import org.example.app.server.api_v1.entity.User;
import org.example.app.server.api_v1.model.CreateUserModel;
import org.example.app.server.api_v1.utils.ActionAnswer;
import org.example.app.server.api_v1.utils.CreateUpdateParams;

public class CreateController {

    public ActionAnswer<User> controllerCreateUser(DbConnectInit connect, CreateUpdateParams createParams) {
        CreateUserModel createUserModel = new CreateUserModel();
        return createUserModel.createUser(connect, createParams);
    }
}
