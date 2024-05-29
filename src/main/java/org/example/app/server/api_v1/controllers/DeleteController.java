package org.example.app.server.api_v1.controllers;

import org.example.app.server.api_v1.db_connect.DbConnectInit;
import org.example.app.server.api_v1.entity.User;
import org.example.app.server.api_v1.model.DeleteUserModel;
import org.example.app.server.api_v1.utils.ActionAnswer;

public class DeleteController {
    public ActionAnswer<User> deleteUser(DbConnectInit connection, User user) {
        DeleteUserModel deleteUserModel = new DeleteUserModel(user);
        return deleteUserModel.deleteUser(connection);
    }
}
