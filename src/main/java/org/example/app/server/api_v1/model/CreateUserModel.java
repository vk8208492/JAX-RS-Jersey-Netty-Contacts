package org.example.app.server.api_v1.model;

import org.example.app.server.api_v1.db_connect.DbConnectInit;
import org.example.app.server.api_v1.entity.User;
import org.example.app.server.api_v1.repository.UserRepository;
import org.example.app.server.api_v1.utils.ActionAnswer;
import org.example.app.server.api_v1.utils.CreateUpdateParams;


public class CreateUserModel {

    public CreateUserModel() {
    }

    public ActionAnswer<User> createUser (DbConnectInit connection, CreateUpdateParams createParams) {
        User newUser = new User(createParams.getFirstName(), createParams.getLastName(), createParams.getEmail(), createParams.getPhone());
        UserRepository userRepository = new UserRepository(connection);
        return userRepository.create(newUser);
    }

}
