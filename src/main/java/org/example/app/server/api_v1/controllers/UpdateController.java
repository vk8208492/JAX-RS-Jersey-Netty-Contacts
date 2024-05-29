package org.example.app.server.api_v1.controllers;

import org.example.app.server.api_v1.db_connect.DbConnectInit;
import org.example.app.server.api_v1.entity.User;
import org.example.app.server.api_v1.model.UpdateUserModel;
import org.example.app.server.api_v1.utils.ActionAnswer;
import org.example.app.server.api_v1.utils.CreateUpdateParams;

public class UpdateController {


    public ActionAnswer<User> updateUser(DbConnectInit connection, User user, CreateUpdateParams createUpdateParams) {
        UpdateUserModel updateUserModel = new UpdateUserModel(user, createUpdateParams);
        return updateUserModel.updateUser(connection);

    }
}
