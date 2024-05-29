package org.example.app.server.api_v1.model;

import org.example.app.server.api_v1.db_connect.DbConnectInit;
import org.example.app.server.api_v1.entity.User;
import org.example.app.server.api_v1.repository.UserRepository;
import org.example.app.server.api_v1.utils.ActionAnswer;


public class DeleteUserModel {
    private final Long id;

    public DeleteUserModel(User user) {
        this.id = user.getId();
    }

    public ActionAnswer<User> deleteUser(DbConnectInit connection) {
        UserRepository userRepository = new UserRepository(connection);
        return userRepository.delete(id);
    }
}
